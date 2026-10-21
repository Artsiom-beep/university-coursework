# university-coursework

Labs and small projects from my Applied Computer Science studies, 2025-2026.
Dates in brackets are roughly when I did each one.

## Java - algorithms and data structures

- `sorting` - selection, insertion, merge sort, quick sort (recursive and iterative) (Oct 2025)
- `hash-tables` - simple hashing, then open addressing with resizing (Nov 2025)
- `red-black-tree` - left-leaning red-black tree + a map on top of it, with tests (Nov 2025)
- `trees` - my first Huffman tree (Nov 2025)
- `huffman-coding` - Huffman compression for bytes and for text (Dec 2025)
- `exercise-6` - price table exercise (Dec 2025)

## Java - basics

- `first-steps`, `lessons` - first programs, loops, arrays, a small console game (spring 2025)
- `oop-kids`, `oop-kids-2`, `packages`, `exceptions` - OOP exercises (May 2025)
- `swing-game` - two-level Swing game, WASD controls (May 2025)
- `quiz-game` - Swing quiz with levels, a level editor and a top players list (May 2025)
- `jimp` - generics, iterators, collections (summer 2025)

## Python

- `course` - exercises from a Python course: basics, OOP, exceptions, working with APIs (Nov-Dec 2025)
- `board-game`, `oop-decorators`, `text-adventure-maze` - small projects from the same course (Dec 2025)
- `ml-model-comparison` - team project: human action recognition, comparing ResNet50, EfficientNetB0 and MobileNetV2 (Jan-Feb 2026)
- `data-analysis-revision` - pandas and stats revision before exams (summer 2026)

## C / C++

- `first` - first C and C++ programs (Feb 2025)
- `c` - graph file converter (CSRRG) (Mar 2025)

## Running

Java projects compile with plain `javac`, e.g.

```
javac -d out $(find java/algorithms-data-structures/sorting -name "*.java" -not -path "*/test/*")
java -cp out org.example.Main
```

Tests need JUnit 5 (and AssertJ for `red-black-tree`).

The DeepL example in `python/course/ProjectFourpointTwo` reads the key from `DEEPL_AUTH_KEY`.
