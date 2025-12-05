import os
import requests
import json

baseurl = 'https://api.chucknorris.io/jokes/random'
response = requests.get(baseurl, params={'limitTo': ['nerdy']})
outputs = response.json()
outputs = json.loads(response.text)
print("Today's joke:")
joke = outputs["value"]
print(joke)
url = "https://api-free.deepl.com/v2/translate"
params = {
    "auth_key": os.environ["DEEPL_AUTH_KEY"],
    "text": joke,
    "target_lang": "RU"
}
resp = requests.post(url, data = params)
trans = resp.json()
print(trans['translations'][0]['text'])

# import json
# import requests
# itunes_parameters = {
#     "term": "Taylor Swift",
#     "media": "music",
#     "entity": "song",
#     "limit": 50
# }
#
# itunes_base_url = "https://itunes.apple.com/search"
#
# itunes_response = requests.get(itunes_base_url, params=itunes_parameters)
#
# data = itunes_response.json()
# 
# for result in data["results"]:
#     print(result["trackName"])