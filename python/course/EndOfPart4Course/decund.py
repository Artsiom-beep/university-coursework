import functools
import time

def timer(func):
    def wrapper(*args, **kwargs):
        start = time.time()
        result = func(*args, **kwargs)
        print("Время выполнения:", time.time() - start, "Time set -", args[0])
        return result
    return wrapper

@timer
def load_data(a):
    time.sleep(a)
    print("Загружено")

@timer
def process_data(b):
    time.sleep(b)
    print("Обработано")


load_data(1)
process_data(3)

def repeat(n):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for i in range(n):
                func(*args, **kwargs)
        return wrapper
    return decorator

@repeat(5)
def greet(name):
    print("Hello", name)

greet("Alice")

@functools.cache
def factorial(n):
    if(n == 1):
        return 1
    else:
        return n * factorial(n - 1)

print(factorial(5))