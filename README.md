# University Coursework

Code I wrote while studying Applied Computer Science (2025–2026): Java, Python and C.
Each folder is a self-contained exercise or small project. Dates show when the work was done.

## Algorithms & data structures (Java)

| Project | What it does | Done | Tests |
|---|---|---|---|
| [sorting](java/algorithms-data-structures/sorting) | Selection, insertion, merge, recursive and iterative quick sort | Oct 2025 | JUnit 2/2 |
| [hash-tables](java/algorithms-data-structures/hash-tables) | Simple hashing, then a hash table with open addressing and resizing | Nov 2025 | — |
| [red-black-tree](java/algorithms-data-structures/red-black-tree) | Left-leaning red-black tree and a map built on it: put, get, deleteMin | Nov 2025 | JUnit 32/32 |
| [trees](java/algorithms-data-structures/trees) | First Huffman tree and encoder | Nov 2025 | JUnit 1/1 |
| [huffman-coding](java/algorithms-data-structures/huffman-coding) | Huffman compression for bytes and for text | Dec 2025 | — |
| [exercise-6](java/algorithms-data-structures/exercise-6) | Price table exercise with random data | Dec 2025 | — |

## Java basics

| Project | What it does | Done |
|---|---|---|
| [first-steps](java/basics/first-steps) | First Java programs, including an MD5 hash | Apr 2025 |
| [lessons](java/basics/lessons) | Loops, arrays and a console game | May 2025 |
| [oop-kids](java/basics/oop-kids), [oop-kids-2](java/basics/oop-kids-2) | Inheritance and polymorphism: transport, students, teachers | May 2025 |
| [packages](java/basics/packages) | Packages and classes: a robot moving on coordinates | May 2025 |
| [swing-game](java/basics/swing-game) | Two-level Swing game controlled with the keyboard | May 2025 |
| [exceptions](java/basics/exceptions) | Exceptions and interfaces: calculator | May 2025 |
| [quiz-game](java/quiz-game) | Swing quiz with levels, a level editor and a top-players table | May 2025 |
| [jimp](java/jimp) | Generics, iterators and collections (shopping cart) | Jun–Sep 2025 |

## Python

| Project | What it does | Done |
|---|---|---|
| [course](python/course) | Course exercises: basics, OOP, exceptions, APIs, binary tree | Nov–Dec 2025 |
| [board-game](python/board-game) | Console board game on a 2D grid | Dec 2025 |
| [oop-decorators](python/oop-decorators) | Decorators and multiple inheritance | Dec 2025 |
| [text-adventure-maze](python/text-adventure-maze) | Text adventure: maze, caves, monsters, items | Dec 2025 |
| [ml-model-comparison](python/ml-model-comparison) | Human action recognition: transfer learning with ResNet50, EfficientNetB0 and MobileNetV2, results and plots (team project) | Jan–Feb 2026 |
| [data-analysis-revision](python/data-analysis-revision) | pandas, quantiles, matrices and text parsing on sample CSV data | Jun–Jul 2026 |

## C / C++

| Project | What it does | Done |
|---|---|---|
| [first](c-cpp/first) | First C and C++ programs | Feb 2025 |
| [c](c-cpp/c) | Graph file format converter (CSRRG) and a graph exercise | Mar 2025 |

## Running

```bash
# Java: compile and run any folder
javac -d out $(find java/algorithms-data-structures/sorting -name "*.java" -not -path "*/test/*")
java -cp out org.example.Main

# Python
python python/text-adventure-maze/main.py

# C
gcc -o convertor c-cpp/c/convertor.c
```

Tests use JUnit 5 (and AssertJ for `red-black-tree`).
