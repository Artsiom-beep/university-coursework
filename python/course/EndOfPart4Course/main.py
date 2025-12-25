#Если мы не знаем сколько элементов передадим, то можем использовать аргс
def summ(*args):
    res = 0
    for i in args:
        res += i
    return res


print(summ(1, 2, 3))
a = [1, 2, 3, 4, 5]
# print(summ(a)) - так писать нельзя - будет ошибка
print(summ(*a)) #- а вот так уже можно

def create_usr_profile(**kwargs):
    user_profile = {}
    for key, value in kwargs.items():
        user_profile[key] = value
    return user_profile

b = create_usr_profile(name = "Alice", age = 20, height = 180)
print(b)

# И точно так же и тут
#create_usr_profile(b) - Ошибка
create_usr_profile(**b) # - ОК

def smth(*args, **kwargs):
    print(args)
    print(kwargs)

smth(1, 2, 4, name = "Alice", age = 20, height = 180)

def debug(func):
    def wrapper(*args, **kwargs):
        print(args)
        print(kwargs)
        return func(*args, **kwargs)
    return wrapper

@debug
def add(a, b):
    return a + b

add(1, 2, name = "Alice", age = 20, height = 180)