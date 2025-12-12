# def addLogging(func): # The argument, func is a function
#
#     def wrapper(x): # x is the argument that we're going to pass to func
#         print(f"About to call the function with argument {x}")
#         result = func(x) # actually call our function and store the result
#         print(f"Done with the function with argument {x}. Result: {result}")
#         return result # return whatever our function returned
#
#     return wrapper # return our new function
#
# def double(x):
#     print("Inside double")
#     return x * 2
#
# logged_double = addLogging(double)
#
# double_3 = logged_double(3)
# print(f"logged_double(3) returned {double_3}")
#
# print("-"*20)
#
# logged_add_one = addLogging(lambda x: x + 1)
# ten_plus_1 = logged_add_one(10)
# print(f"logged_add_one(10) returned {ten_plus_1}")








# def addLogging(func): # The argument, func is a function
#
#     def wrapper(x): # x is the argument that we're going to pass to func
#         print(f"About to call the function with argument {x}")
#         result = func(x) # actually call our function and store the result
#         print(f"Done with the function with argument {x}. Result: {result}")
#         return result # return whatever our function returned
#
#     return wrapper # return our new function
#
# @addLogging # equivalent to double = addLogging(double) - мы передаем функции addLogging эту функцию
# def double(x):
#     print("Inside double")
#     return x * 2
#
# double(10)







# This is a decorator function that takes another function as an argument.
# def passwordProtect(func):
#
#     # This inner function is the one that will actually be called
#     # when we use the decorator on another function.
#     def wrappedFunc():
#         password = input('Enter the password to call the function:')
#
#         if password == 'password123': # correct password? then call the original function
#             func()
#         else: # If the password is not correct, deny access
#             print("Access denied. Sorry, you need to enter the correct password to get the secret message.")
#
#     return wrappedFunc
#
#
# @passwordProtect
# def printSecretMessage():
#     secretMessage = "Shhh...this is a secret message"
#
#     # We print a series of "~" characters the same length as the message,
#     # then the message itself, then another series of "~" characters.
#     print("~" * len(secretMessage))
#     print(secretMessage)
#     print("~" * len(secretMessage))
#
# # By adding the decorator, we prompt the user for a password before printing the secret message.
# printSecretMessage()






# def addLogging(func): # The argument, func is a method of a class
#
#     def wrapper(self, x): # x is the argument that we're going to pass to func
#         print(f"About to call the method with argument {x}")
#         result = func(self, x) # actually call the method and store the result
#         print(f"Done with the method invocation with argument {x} on instance {self}. Result: {result}")
#         return result # return whatever our function returned
#
#     return wrapper # return our new function
#
# class Car:
#     def __init__(self, make, model, color, mileage):
#         self.make = make
#         self.model = model
#         self.color = color
#         self.mileage = mileage
#
#     @addLogging
#     def drive(self, miles):
#         self.mileage += miles
#         return self.mileage
#
#     @addLogging
#     def rePaint(self, color):
#         self.color = color
#
#     def __str__(self):
#         return(f"***{self.color} {self.make} {self.model} with {self.mileage} miles***")
#
# corvette = Car("Chevrolet", "Corvette", "red", 0)
#
# corvette.drive(100)
# print("-"*20)
# corvette.rePaint("blue")
# print("-"*20)
# corvette.drive(6)





def addBeep(cls): #cls = class
    cls.beep = lambda self: print(f"{self.model} says 'Beep!'")
    return cls

@addBeep
class Car:

    count = 0


    def __init__(self, make, model, color, mileage):
        self.make = make
        self.model = model
        self.__color = color
        self.mileage = mileage
        Car.count += 1

#Проперти позволяют нам обращаться к закрытым данным более естественно
    @property
    def color(self):
        print("Hello!")
        return self.__color

    #Setter должен называться так же, как и property, то есть color, а не setcolor. Иначе Python просто не связывает их между собой.

    @color.setter
    def color(self, color):
        self.__color = color

    #Так же, если мы хотим иметь что, то именно в классе, а не в обьектах, то можно сделать вот что
    #для этого мы создали переменную класса - count

    @classmethod
    def getCount(cls):
        return cls.count

    #Еще можно сделать статик метод - он будет привязан только к классу
    @staticmethod
    def about():
        print("This class describes cars")

mustang = Car("Ford", "Mustang", "blue", 0)
#mustang.beep() # Mustang says 'Beep!'

bmw = Car("BMW", "M5", "black", 0)
Lada = Car("Lada", "Terminator", "white", 9999)
#Вот так
print(mustang.color)
mustang.color = "red"
print(mustang.color)
print("Cars created:", Car.getCount())
Car.about()

