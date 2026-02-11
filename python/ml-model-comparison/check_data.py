import os
import tensorflow as tf

DATA_DIR = r".\data\Human Action Recognition"
TRAIN_DIR = os.path.join(DATA_DIR, "train")

IMG_SIZE = (224, 224)
BATCH = 16

ds = tf.keras.utils.image_dataset_from_directory(
    TRAIN_DIR,
    image_size=IMG_SIZE,
    batch_size=BATCH,
    shuffle=True,
    seed=42,
)

print("Classes:", len(ds.class_names))
print(ds.class_names)

for images, labels in ds.take(1):
    print("Batch images:", images.shape)
    print("Batch labels:", labels.shape)
