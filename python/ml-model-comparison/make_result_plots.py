# make_results_plots.py
# Читает results.json и автоматически рисует нужные wykresy do raportu
# Wyniki zapisuje w folderze: plots_results/

import json
from pathlib import Path

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt


RESULTS_JSON = "results.json"
OUT_DIR = Path("plots_results")


def load_results(path: str):
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def get_class_labels_from_report(report: dict):
    skip = {"accuracy", "macro avg", "weighted avg"}
    labels = [k for k in report.keys() if k not in skip]
    return labels


def save_model_comparison(results):
    df = pd.DataFrame([{
        "model": r["model"],
        "val_acc": float(r["val_acc"]),
        "macro_f1": float(r["macro_f1"]),
        "checkpoint": r.get("checkpoint", "")
    } for r in results]).sort_values(["macro_f1", "val_acc"], ascending=False)

    df.to_csv(OUT_DIR / "model_comparison.csv", index=False, encoding="utf-8")

    plt.figure(figsize=(7, 4))
    plt.bar(df["model"], df["val_acc"])
    plt.ylim(0, 1)
    plt.title("Porównanie modeli — val_accuracy")
    plt.ylabel("val_accuracy")
    plt.tight_layout()
    plt.savefig(OUT_DIR / "model_val_accuracy.png", dpi=200)
    plt.close()

    plt.figure(figsize=(7, 4))
    plt.bar(df["model"], df["macro_f1"])
    plt.ylim(0, 1)
    plt.title("Porównanie modeli — macro F1")
    plt.ylabel("macro F1")
    plt.tight_layout()
    plt.savefig(OUT_DIR / "model_macro_f1.png", dpi=200)
    plt.close()

    return df


def save_confusion_matrices(cm, labels, model, out_dir: Path):
    cm = np.array(cm, dtype=np.int64)

    # 1) RAW (counts)
    plt.figure(figsize=(8, 8))
    plt.imshow(cm)
    plt.title(f"Confusion matrix (counts) — {model}")
    plt.xticks(range(len(labels)), labels, rotation=90)
    plt.yticks(range(len(labels)), labels)
    plt.tight_layout()
    plt.savefig(out_dir / f"confusion_matrix_counts_{model}.png", dpi=200)
    plt.close()

    # 2) NORMALIZED (row-wise, %)
    row_sum = cm.sum(axis=1, keepdims=True)
    cm_norm = np.divide(cm, row_sum, out=np.zeros_like(cm, dtype=float), where=row_sum != 0)

    plt.figure(figsize=(8, 8))
    plt.imshow(cm_norm, vmin=0.0, vmax=1.0)
    plt.title(f"Confusion matrix (normalized) — {model}")
    plt.xticks(range(len(labels)), labels, rotation=90)
    plt.yticks(range(len(labels)), labels)
    plt.tight_layout()
    plt.savefig(out_dir / f"confusion_matrix_norm_{model}.png", dpi=200)
    plt.close()


def top_confusions(cm, labels, top_n=15):
    cm = np.array(cm, dtype=np.int64)
    n = cm.shape[0]
    pairs = []
    for i in range(n):
        for j in range(n):
            if i == j:
                continue
            cnt = int(cm[i, j])
            if cnt > 0:
                pairs.append((cnt, labels[i], labels[j]))
    pairs.sort(reverse=True, key=lambda x: x[0])
    rows = [{"count": c, "true": t, "pred": p} for (c, t, p) in pairs[:top_n]]
    return pd.DataFrame(rows)


def save_best_model_class_metrics(best_r, labels):
    rep = best_r["report"]

    rows = []
    for lab in labels:
        rows.append({
            "label": lab,
            "precision": float(rep[lab]["precision"]),
            "recall": float(rep[lab]["recall"]),
            "f1": float(rep[lab]["f1-score"]),
            "support": float(rep[lab]["support"]),
        })
    df = pd.DataFrame(rows).sort_values("f1")
    df.to_csv(OUT_DIR / f"per_class_metrics_{best_r['model']}.csv", index=False, encoding="utf-8")

    plt.figure(figsize=(10, 4))
    plt.bar(df["label"], df["f1"])
    plt.ylim(0, 1)
    plt.xticks(rotation=45, ha="right")
    plt.title(f"F1-score per klasa — {best_r['model']}")
    plt.ylabel("F1")
    plt.tight_layout()
    plt.savefig(OUT_DIR / f"best_model_f1_per_class_{best_r['model']}.png", dpi=200)
    plt.close()

    plt.figure(figsize=(10, 4))
    plt.bar(df["label"], df["precision"])
    plt.ylim(0, 1)
    plt.xticks(rotation=45, ha="right")
    plt.title(f"Precision per klasa — {best_r['model']}")
    plt.ylabel("Precision")
    plt.tight_layout()
    plt.savefig(OUT_DIR / f"best_model_precision_per_class_{best_r['model']}.png", dpi=200)
    plt.close()

    plt.figure(figsize=(10, 4))
    plt.bar(df["label"], df["recall"])
    plt.ylim(0, 1)
    plt.xticks(rotation=45, ha="right")
    plt.title(f"Recall per klasa — {best_r['model']}")
    plt.ylabel("Recall")
    plt.tight_layout()
    plt.savefig(OUT_DIR / f"best_model_recall_per_class_{best_r['model']}.png", dpi=200)
    plt.close()


def main():
    OUT_DIR.mkdir(parents=True, exist_ok=True)

    results = load_results(RESULTS_JSON)
    if not results:
        raise RuntimeError("results.json пустой")

    labels = get_class_labels_from_report(results[0]["report"])

    df_cmp = save_model_comparison(results)

    for r in results:
        model = r["model"]
        cm = r["cm"]
        save_confusion_matrices(cm, labels, model, OUT_DIR)
        df_top = top_confusions(cm, labels, top_n=15)
        df_top.to_csv(OUT_DIR / f"top_confusions_{model}.csv", index=False, encoding="utf-8")

    best_model_name = df_cmp.iloc[0]["model"]
    best_r = next(r for r in results if r["model"] == best_model_name)
    save_best_model_class_metrics(best_r, labels)

    summary = {
        "best_model": best_model_name,
        "models": df_cmp.to_dict(orient="records"),
        "labels": labels,
        "outputs_dir": str(OUT_DIR),
    }
    with open(OUT_DIR / "summary.json", "w", encoding="utf-8") as f:
        json.dump(summary, f, ensure_ascii=False, indent=2)

    print("OK. Saved to:", OUT_DIR.resolve())


if __name__ == "__main__":
    main()
