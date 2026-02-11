import os, json
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report, confusion_matrix

# ---------- настройки ----------
SEED = 42
tf.keras.utils.set_random_seed(SEED)
np.random.seed(SEED)

DATA_DIR = r".\data\Human Action Recognition"
TRAIN_IMG_DIR = os.path.join(DATA_DIR, "train")
CSV_PATH = os.path.join(DATA_DIR, "Training_set.csv")

# быстрее на CPU
IMG_SIZE = (160, 160)
BATCH = 16
VAL_SPLIT = 0.2

# ---------- читаем CSV ----------
df = pd.read_csv(CSV_PATH)
df["path"] = df["filename"].apply(lambda x: os.path.join(TRAIN_IMG_DIR, x))

class_names = sorted(df["label"].unique())
label_to_id = {name: i for i, name in enumerate(class_names)}
df["label_id"] = df["label"].map(label_to_id)

train_df, val_df = train_test_split(
    df, test_size=VAL_SPLIT, random_state=SEED, stratify=df["label_id"]
)

AUTOTUNE = tf.data.AUTOTUNE

def load_img(path, label):
    img = tf.io.read_file(path)
    img = tf.image.decode_jpeg(img, channels=3)
    img = tf.image.resize(img, IMG_SIZE)
    img = tf.cast(img, tf.float32)
    return img, label

train_ds = tf.data.Dataset.from_tensor_slices(
    (train_df["path"].values, train_df["label_id"].values)
).shuffle(4000, seed=SEED).map(load_img, num_parallel_calls=AUTOTUNE).batch(BATCH).prefetch(AUTOTUNE)

val_ds = tf.data.Dataset.from_tensor_slices(
    (val_df["path"].values, val_df["label_id"].values)
).map(load_img, num_parallel_calls=AUTOTUNE).batch(BATCH).prefetch(AUTOTUNE)

num_classes = len(class_names)
print("Classes:", num_classes, class_names)

# ---------- аугментации ----------
augment = keras.Sequential([
    layers.RandomFlip("horizontal"),
    layers.RandomRotation(0.05),
    layers.RandomZoom(0.1),
    layers.RandomContrast(0.1),
], name="augment")

# ---------- фабрика моделей ----------
def build_model(backbone_name: str):
    inputs = keras.Input(shape=IMG_SIZE + (3,))
    x = augment(inputs)

    if backbone_name == "resnet50":
        preprocess = keras.applications.resnet.preprocess_input
        base = keras.applications.ResNet50(include_top=False, weights="imagenet", input_shape=IMG_SIZE + (3,))
    elif backbone_name == "efficientnetb0":
        preprocess = keras.applications.efficientnet.preprocess_input
        base = keras.applications.EfficientNetB0(include_top=False, weights="imagenet", input_shape=IMG_SIZE + (3,))
    elif backbone_name == "mobilenetv2":
        preprocess = keras.applications.mobilenet_v2.preprocess_input
        base = keras.applications.MobileNetV2(include_top=False, weights="imagenet", input_shape=IMG_SIZE + (3,))
    else:
        raise ValueError("unknown backbone")

    base.trainable = False

    x = preprocess(x)
    x = base(x, training=False)
    x = layers.GlobalAveragePooling2D()(x)
    x = layers.Dropout(0.25)(x)
    outputs = layers.Dense(num_classes, activation="softmax")(x)

    model = keras.Model(inputs, outputs, name=f"har_{backbone_name}")
    return model, base

def train_one(name: str):
    model, base = build_model(name)

    model.compile(
        optimizer=keras.optimizers.Adam(1e-3),
        loss=keras.losses.SparseCategoricalCrossentropy(),
        metrics=["accuracy"]
    )

    ckpt = f"best_{name}.keras"
    callbacks = [
        keras.callbacks.ModelCheckpoint(ckpt, monitor="val_accuracy", save_best_only=True),
        keras.callbacks.EarlyStopping(monitor="val_accuracy", patience=2, restore_best_weights=True),
        keras.callbacks.ReduceLROnPlateau(monitor="val_loss", factor=0.5, patience=1, min_lr=1e-6),
    ]

    # этап 1 (быстрее)
    model.fit(train_ds, validation_data=val_ds, epochs=4, callbacks=callbacks, verbose=1)

    # этап 2 (быстрее)
    base.trainable = True
    fine_tune_at = int(len(base.layers) * 0.7)
    for layer in base.layers[:fine_tune_at]:
        layer.trainable = False

    model.compile(
        optimizer=keras.optimizers.Adam(1e-4),
        loss=keras.losses.SparseCategoricalCrossentropy(),
        metrics=["accuracy"]
    )
    model.fit(train_ds, validation_data=val_ds, epochs=3, callbacks=callbacks, verbose=1)

    best = keras.models.load_model(ckpt)

    y_true = np.concatenate([y.numpy() for _, y in val_ds], axis=0)
    y_prob = best.predict(val_ds, verbose=0)
    y_pred = np.argmax(y_prob, axis=1)

    acc = float((y_pred == y_true).mean())
    rep = classification_report(y_true, y_pred, target_names=class_names, output_dict=True, zero_division=0)
    macro_f1 = float(rep["macro avg"]["f1-score"])
    cm = confusion_matrix(y_true, y_pred).tolist()

    return {"model": name, "val_acc": acc, "macro_f1": macro_f1, "checkpoint": ckpt, "report": rep, "cm": cm}

results = []
for name in ["resnet50", "efficientnetb0", "mobilenetv2"]:
    r = train_one(name)
    results.append(r)
    print("DONE:", name, "acc=", r["val_acc"], "macro_f1=", r["macro_f1"])

with open("results.json", "w", encoding="utf-8") as f:
    json.dump(results, f, ensure_ascii=False, indent=2)

print("Saved: results.json")
