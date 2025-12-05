import json
import copy

a = "{\"status\":\"ok\",\"value\":42}"
d = json.loads(a)
print(d)
print(type(d))

plant = {
    'name': "Rosa",
    "weight": "42",
    'abilities': ["swim", "fly"]
}

print(json.dumps(plant, sort_keys = True, indent = 2))
with open("TestJsonText.json", "w") as tek:
    json.dump(plant, tek, indent=2)

a = [1, [2, 3, [4, 5], 6], [7, 8], 9]
b = a
c = a[:]
d = copy.deepcopy(a)

a.append(10)
a[1].append(11)
a[1][2].append(12)
print("\n")
print(a)
print(b)
print(c)
print(d)

