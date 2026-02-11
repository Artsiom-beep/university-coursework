import os
import pandas as pd
import tensorflow as tf
from sklearn.model_selection import train_test_split

DATA_DIR = r".\data\Human Action Recognition"
TRAIN_IMG_DIR = os.path.join(DATA_DIR, "train")
CSV_PATH = os.path.join(DATA_DIR, "Training_set.csv")

IMG_SIZE = (224, 224)
BATCH = 16
SEED = 42

df = pd.read_csv(CSV_PATH)
df["path"] = df["filename"].apply(lambda x: os.path.join(TRAIN_IMG_DIR, x))

missing = df[~df["path"].apply(os.path.exists)]
print("Rows:", len(df))
print("Missing files:", len(missing))

class_names = sorted(df["label"].unique())
label_to_id = {name: i for i, name in enumerate(class_names)}
df["label_id"] = df["label"].map(label_to_id)

print("Classes:", len(class_names))
print(class_names)

train_df, val_df = train_test_split(
    df, test_size=0.2, random_state=SEED, stratify=df["label_id"]
)

AUTOTUNE = tf.data.AUTOTUNE

def load_img(path, label):
    img = tf.io.read_file(path)
    img = tf.image.decode_jpeg(img, channels=3)
    img = tf.image.resize(img, IMG_SIZE)
    img = tf.cast(img, tf.float32) / 255.0
    return img, label

train_ds = tf.data.Dataset.from_tensor_slices(
    (train_df["path"].values, train_df["label_id"].values)
).shuffle(2000, seed=SEED).map(load_img, num_parallel_calls=AUTOTUNE).batch(BATCH).prefetch(AUTOTUNE)

val_ds = tf.data.Dataset.from_tensor_slices(
    (val_df["path"].values, val_df["label_id"].values)
).map(load_img, num_parallel_calls=AUTOTUNE).batch(BATCH).prefetch(AUTOTUNE)

for x, y in train_ds.take(1):
    print("Batch images:", x.shape)
    print("Batch labels:", y.shape)
    print("First labels:", y[:10].numpy())
