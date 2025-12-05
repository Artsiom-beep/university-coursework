import requests
import json

default_URL = "https://itunes.apple.com/search"

parameters = {
    "country": "US",
    "media": "music",
    "limit": "5",
    "term": "Drake"
}
response = requests.get(default_URL, params=parameters)
data = response.json()
for i in range(5):
    print(data["results"][i]["trackName"])


# import requests
# import json
#
# default_URL = "https://itunes.apple.com/search"
#
# parameters = {
#     "country": "US",
#     "media": "music",
#     "limit": 5,          # можно как число
#     "term": "Drake"
# }
#
# response = requests.get(default_URL, params=parameters)
# data = response.json()
#
# # Всегда безопасно проходим по количеству найденных результатов
# for item in data.get("results", []):
#     print(item.get("trackName", "No track name"))