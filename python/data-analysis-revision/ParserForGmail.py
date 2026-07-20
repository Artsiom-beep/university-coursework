
#Парсер для считывания социальных сетей
def firstVersionParser(stringTrue):
    exampleString = stringTrue
    listOfExample = exampleString.split()
    listOfExampleSecond = []

    for i in range(len(listOfExample)):
        listOfExample[i] = listOfExample[i].replace(",", "")
        listOfExample[i] = listOfExample[i].replace(":", "")

    for i in range(0, len(listOfExample), 2):
        if i + 1 < len(listOfExample):
            listOfExampleSecond.append((listOfExample[i], listOfExample[i + 1]))

    finalDict = dict(listOfExampleSecond)
    return finalDict

def secondVersionParser():
    exampleString = stringTrue
    listOfPairsExample = exampleString.split(",")

    result = {}

    for pair in listOfPairsExample:
        key, value = pair.split(": ", 1)
        key = key.strip()
        value = value.strip()
        result[key] = value

    return result


stringTrue: str = "Email: abc@gmail.com, Instagram: abcUser"

a = firstVersionParser(stringTrue)
b = secondVersionParser()

print(a)
print(b)