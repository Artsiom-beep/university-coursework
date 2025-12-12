#Множественное наследование
class Swimmer:
    def swim(self):
        print('swimming')

class Runner:
    def running(self):
        print('running')

class Flyer:
    def fly(self):
        print('fly')


class Duck(Swimmer, Flyer, Runner):

    def __init__(self, name):
        self.name = name

bird = Duck('Bob')
bird.swim()
bird.fly()
bird.running()