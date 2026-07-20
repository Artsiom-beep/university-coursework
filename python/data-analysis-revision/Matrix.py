from random import randrange


class Matrix:
    def __init__(self, matrix):
        if len(matrix) == 0:
            raise ValueError("Matrix cannot be empty")

        self.matrix = matrix
        self.rows = len(matrix)
        self.cols = len(matrix[0])
        self.matrixSize = len(matrix)

        if not self.checkIfCorrectMatrix():
            raise ValueError("Incorrect matrix shape")

    @classmethod
    def randomMatrix(cls, matrixSize):
        matrix = []

        for i in range(matrixSize):
            row = []

            for j in range(matrixSize):
                row.append(randrange(0, 100))

            matrix.append(row)

        return cls(matrix)

    @classmethod
    def identityMatrix(cls, matrixSize):
        matrix = []

        for i in range(matrixSize):
            row = []

            for j in range(matrixSize):
                if i == j:
                    row.append(1)
                else:
                    row.append(0)

            matrix.append(row)

        return cls(matrix)

    def __str__(self):
        text = ""

        for row in self.matrix:
            text += str(row) + "\n"

        return text

    def __add__(self, B):
        if not self.checkSameSize(B):
            raise ValueError("Matrices cannot be summed")

        ans = []

        for i in range(self.rows):
            row = []

            for j in range(self.cols):
                row.append(self.matrix[i][j] + B.matrix[i][j])

            ans.append(row)

        return Matrix(ans)

    def __sub__(self, B):
        if not self.checkSameSize(B):
            raise ValueError("Matrices cannot be subtracted")

        ans = []

        for i in range(self.rows):
            row = []

            for j in range(self.cols):
                row.append(self.matrix[i][j] - B.matrix[i][j])

            ans.append(row)

        return Matrix(ans)

    def __mul__(self, B):
        if self.cols != B.rows:
            raise ValueError("Matrices cannot be multiplied")

        ans = []

        for i in range(self.rows):
            row = []

            for j in range(B.cols):
                value = 0

                for k in range(self.cols):
                    value += self.matrix[i][k] * B.matrix[k][j]

                row.append(value)

            ans.append(row)

        return Matrix(ans)

    def det(self):
        if not self.checkIfSquare():
            raise ValueError("Matrices cannot have determinants")

        if self.rows == 1:
            return self.matrix[0][0]

        if self.rows == 2:
            return self.matrix[0][0] * self.matrix[1][1] - self.matrix[0][1] * self.matrix[1][0]

        detAns = 0

        for k in range(self.cols):
            minor = []

            for i in range(1, self.rows):
                row = []

                for j in range(self.cols):
                    if j != k:
                        row.append(self.matrix[i][j])

                minor.append(row)

            detAns += self.matrix[0][k] * ((-1) ** k) * Matrix(minor).det()

        return detAns

    def checkIfCorrectMatrix(self):
        for row in self.matrix:
            if len(row) != self.cols:
                return False

        return True

    def checkIfSquare(self):
        return self.rows == self.cols

    def checkSameSize(self, B):
        return self.rows == B.rows and self.cols == B.cols

    def transpose(self):
        transposed = []

        for j in range(len(self.matrix[0])):
            row = []

            for i in range(len(self.matrix)):
                row.append(self.matrix[i][j])

            transposed.append(row)

        return Matrix(transposed)

