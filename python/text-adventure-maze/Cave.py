import random

from Maze import Maze

class Cave:

    def __init__(self, size):
        self.size = size
        self.levels: list[Maze] = []
        for level in range(size):
            self.levels.append(Maze((random.randint(1, 20), random.randint(1, 20))))
