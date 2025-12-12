#Если мы хотим сделать Родительский класс и класс ребенок, то мы передаем в детский класс класс родитель.
class Parent:

    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return f'{self.name} is {self.age} years old'

    def changeAge(self, new_age):
        self.age = new_age

#Вот так используется супер
class Student(Parent):
    def __init__(self, name, age, know = 0):
        super().__init__(name, age)
        self.knowledge = know

    def __str__(self):
        return f'{self.name} is {self.age} years old and knows {self.knowledge}'

    def yearOfStudy(self):
        super().changeAge(self.age + 1)
        self.knowledge += 1

Alice = Student('Alice', 22, 10)

print(Alice)

Alice.changeAge(20)
Alice.yearOfStudy()

print(Alice)