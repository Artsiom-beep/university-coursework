import random

def multiply_by(a):
    return 3*a

def tripleStaff(list_a):
    new_seq = map(multiply_by, list_a)
   #print(type(new_seq))
    return list(new_seq)

def mutiplyListby(list_a, z):
    return list(map(lambda values: values * z, list_a))

lista = []
for i in range (10):
    lista.append(random.randint(0, 100))
lista2 = tripleStaff(lista)
#lista5 = mutiplyListby(lista, 3)
print(lista2)
#print(lista5)

#abbrevs = ["usa", "esp", "chn", "jpn", "mex", "can", "rus", "rsa", "jam"]
#abbrevs_upper = list(map(lambda x: x.upper(), abbrevs))


def ourIf(x):
    if x % 2 == 0:
        return x

def keepEverythingEven(list_a):
    return list(filter(ourIf, list_a))


def keepSecondVersion(List_a):
    return list(filter(lambda x: x % 2 == 0, List_a))

print(keepEverythingEven(lista2))
print(keepSecondVersion(lista2))


# В чём разница?
# Обе функции удаляют нечётные числа, но:
# В первой — условие основано на том, что чётное число само по себе truthy.
# Во второй — условие основано на логическом True/False.
# ⚠️ Скрытая проблема
# Если список содержит 0, то:
# В первой версии ourIf(0) вернёт 0, но 0 — это falsy, и filter его отбросит.
# Во второй версии lambda вернёт True, и 0 будет сохранён.
# 👉 Значит для списка с нулём результаты отличаются!
# Итог
# Для всех чисел, кроме 0, оба способа дают одинаковый результат.
# При наличии 0 — результаты разные.
# Если хочешь полностью идентичного поведения — первая функция должна возвращать True/False, как во второй.

strin = ["ghjk", "ghhbjkm", "hjk", 'yuhgbhjiok', "jk"]
strin_len = [len(word)*2 for word in strin if len(word) < 5]
print(strin_len)

diction = {
    "Person":
        [
        {"Me" : {"age": 19, "Name" : "Абсолютная истина", "Sex": "CS2"}},
        {"Serafim" : {"age" : 20, "Name" : "Херосима", "Sex": "Gay, BBK/BBC"}},
        {"Arseni" : {"age" : 19, "Name" : "Алибаба", "Sex": "Only with hand"}}
        ]
}

trytotakeme = [person_data["Name"] for person in diction['Person'] 
               for person_data in person.values()
               if person_data["age"] == 19]

print(trytotakeme)

#Also we can do the same like that
# result = []

# for entry in diction["Person"]:
#     for _, data in entry.items():
#         if data["age"] == 19:
#             result.append(data["Name"])

# print(result)

#Функция zip НЕ СУММИРУЕТ - она делает tuples - (1, 4), (2, 5), (3, 6)
a1 = [1, 2, 3]
a2 = [4, 5, 6]
a3 = [8, 9, 10, 11]


def sumofLists(a, b):
    outarr = []
    size = len(a)
    if len(a) != len(b):
        size = min(len(a), len(b))
    j = 0  
    for i in range(size):
        outarr.append(a[i] + b[i])
        j = i

    j += 1

    while len(outarr) < len(a):
        outarr.append(a[j])
        j += 1

    while len(outarr) < len(b):
        outarr.append(b[j])
        j += 1
    
    return outarr

#print(sumofLists(a1, a2))
#print(sumofLists(a1, a3))

a4 = list(zip(a1, a3, a2)) #Если убрать а2 то цикл написанный ниже будет работать корректно - так как у нас 2 элемента в цикле, а не 3 как тут
a5 = []
#for i, j in a4:
#    a5.append(i + j)
print(a4)


givenWord = "worldoftanks"
whichCharsGuessed = "_o__do_ta___"
guessedchars = "agdotaqe"

def checkifthewordispossible(word, guessed, chars):
    if len(word) != len(guessed):
        return False
    for wc, gc in zip(word, guessed):
        if gc == '_' and wc in chars:
            return False
        elif gc != '_' and gc != wc:
            return False
    return True

print(checkifthewordispossible(givenWord, whichCharsGuessed, guessedchars))

# def give_feedback(guess, correct_word):
#     a = []
#     for i, j in zip(guess, correct_word):
#         if i == j:
#             a.append("Y")
#         elif i != j and i in correct_word:
#             a.append("y")
#         else:
#             a.append("-")
#     return a