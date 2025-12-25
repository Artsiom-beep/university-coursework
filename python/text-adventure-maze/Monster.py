class Monster:
    def __init__(self, name, health, damage, coordX, coordY):
        self.name = name
        self.health: int = health
        self.damage: int = damage
        self.coordX = coordX
        self.coordY = coordY