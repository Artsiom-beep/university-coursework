import os
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow import keras

DATA_DIR = r".\data\Human Action Recognition"
TRAIN_IMG_DIR = os.path.join(DATA_DIR, "train")
CSV_PATH = os.path.join(DATA_DIR, "Training_set.csv")

IMG_SIZE = (160, 160)

df = pd.read_csv(CSV_PATH)
df["path"] = df["filename"].apply(lambda x: os.path.join(TRAIN_IMG_DIR, x))

sample_paths = df["path"].sample(8, random_state=42).tolist()

def load_one(path):
    img = tf.io.read_file(path)
    img = tf.image.decode_jpeg(img, channels=3)
    img = tf.image.resize(img, IMG_SIZE)
    img = tf.cast(img, tf.float32)  # 0..255
    return img

x = tf.stack([load_one(p) for p in sample_paths], axis=0)

for ckpt in ["best_resnet50.keras", "best_efficientnetb0.keras", "best_mobilenetv2.keras"]:
    model = keras.models.load_model(ckpt)
    probs = model.predict(x, verbose=0)
    print(ckpt, "OK", "shape=", probs.shape, "sum_first=", float(np.sum(probs[0])))
