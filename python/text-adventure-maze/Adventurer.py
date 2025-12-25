import random

from Item import Item


class Adventurer:
    def __init__(self, name = "Artsiom", coordX = 0, coordY = 0):
        self.name: str = name
        self.damage: int = random.randint(1,10)
        self.health: int = random.randint(10, 100)
        self.coordX = coordX
        self.coordY = coordY
        self.backpack: list[Item] = []
        self.itemInHand: Item = Item('None', 0)
        self.backpack.append(self.itemInHand)



    def dealDamage(self):
        return self.damage + self.itemInHand.damage