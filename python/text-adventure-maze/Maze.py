class Maze:
    def __init__(self, size: tuple[int, int]):
        if size[0] < 1 or size[1] < 1:
            self.size = (10, 10)
        else:
            self.size = size

        width, height = self.size

        self.map: list[list[str]] = []
        for y in range(height):
            row = []
            for x in range(width):
                row.append(".")
            self.map.append(row)

        for x in range(width):
            self.map[0][x] = "#"
            self.map[height - 1][x] = "#"

        for y in range(height):
            self.map[y][0] = "#"
            self.map[y][width - 1] = "#"

    def draw(self):
        for row in self.map:
            print("".join(row))