def Devision(delimoe, delitel, skolko_raz_wywesti = None):
    if skolko_raz_wywesti is None:
        skolko_raz_wywesti = 1

    if delitel == 0:
        raise ValueError("We cannot devide by zero!")

    ans = float(delimoe) / float(delitel)
    for i in range(skolko_raz_wywesti):
        print(ans)



try:
    Devision(4, 3)
except ValueError as e:
    print(e)


x = int(input("Enter a number: "))
a = int(input("Enter a number: "))

double = lambda x, a: x * a
print(double(x, a))


students = [
    {"name": "Tom", "grade": 4},
    {"name": "Anna", "grade": 5},
    {"name": "Bob", "grade": 3}
]

students_sorted = sorted(students,
                         key=lambda student: student["grade"]) #returned new dictionary

students.sort(key=lambda student: student["grade"], reverse = True) #modified existing but in reversed order



