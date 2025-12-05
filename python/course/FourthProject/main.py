# import statements for necessary Python modules
import requests
import re

def get_rhymes(word):
    baseurl = "https://api.datamuse.com/words"
    params_diction = {}
    params_diction["rel_rhy"] = word
    params_diction["max"] = "3"
    resp = requests.get(baseurl, params=params_diction)
    word_ds = resp.json()
    return [d['word'] for d in word_ds]



def get_synonyms(word):
    baseurl = "https://api.datamuse.com/words"
    params_diction = {}
    params_diction["ml"] = word
    params_diction["max"] = "3"
    resp = requests.get(baseurl, params=params_diction)
    print(resp.url)
    word_ds = resp.json()
    return [d['word'] for d in word_ds]

#это написал чат
def clean_definition(def_text):
    return re.sub(r'^\w+\t(?:\([^)]*\))?\s*', '', def_text)

#тут чат мне помог
def get_meaning(words):
    baseurl = 'https://api.datamuse.com/words'
    meanings = []

    for word in words:
        resp = requests.get(baseurl, params={
            "sp": word,
            "md": "d"
        })
        data = resp.json()

        if data and "defs" in data[0] and data[0]["defs"]:
            # Берём ТОЛЬКО первое определение
            first_def = clean_definition(data[0]["defs"][0])
            meanings.append(first_def)
        else:
            meanings.append("No definition found")

    return meanings

slowa = []
slowo = input("Enter the words you wanna look for: ").lower()
for i in range (2):
    slowa.append(input("Provide word: ").lower())
print(get_rhymes(slowo))
print(get_synonyms(slowo))
print(get_meaning(slowa))

