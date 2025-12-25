items = ['a', 'b']
try:
    10 / 0
    print("a")
    third = items[2]
    print('b')
except ZeroDivisionError:
    print("ZeroDivisionError")
except IndexError:
    print("error")
    third = 'false'
except Exception:
    print("error")

print("I want this to be written")

