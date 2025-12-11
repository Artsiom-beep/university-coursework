import pandas as pd
from Board import Board

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def getX(self):
        return self.x

    def getY(self):
        return self.y

    #если мы хотим сделать переменную типа private, то мы должны написать __ перед ней
    __name = "Bob"
    def getName(self):
        return self.__name

    #если мы хотим выписать объект, что бы не выписывать по 1 значению мы пишем вот такой метод - так объект выведется как мы захотим
    def __str__(self):
        return f"Point {self.__name}: X - {self.x}, Y - {self.y}"

    #Точно такую же вещь можно провернуть и с плюсом - нужно показать питону как прибавлять объекты. Так можно со всеми штуками, как -, / и *

    def __add__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    #Если мы хотим посортировать по какому, то признаку, то нам нужно написать функцию, как мы будем сортировать
    def nameOfSorterThatWeChoose(self):
        return self.x

    #Есть и другой способ - __lt__ Он работает как compareTo в Java. В примере ниже мы сортируем по иксам пункта
    def __lt__(self, other):
        return self.x < other.x

    #Когда мы обращаемся к объекту класса, что бы что-то выписать или сделать, то Питон смотрит сначала есть ли эта переменная в этом объекте
    #потом она смотрит на класс. Так что если у нас будут 2 переменные с одинаковым названием, то Питон возьмет то, что лежит в объекте


po = Point(0, 5)
po2 = Point(2, 1)
print(po.getName())
print(po)
print(po + po2)
for i in (sorted([po, po2], key = Point.nameOfSorterThatWeChoose)):
    print(i)

#Если мы хотим взять класс откуда: то еще, то мы импортируем его как показано выше
# bo = Board(5, 5)
# a = bo.printBoard()
# df = pd.DataFrame(a, columns=["x", "y", "z", "c", "b"])
# print(df)

