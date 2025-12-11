class Board:

    def __init__(self, width = 10, height = 10):
        self.width = width
        self.height = height
        self.a = []
        for i in range(width):
            self.a.append([])
            for j in range(height):
                self.a[i].append(".")


    def printBoard(self):
        for row in range(self.height):
            for col in range(self.width):
                print(self.a[row][col], end=" ")
            print()
        return self.a