import csv
punctuation_chars = ["'", '"', ",", ".", "!", ":", ";", '#', '@']

def strip_punctuation(x):
    for p in punctuation_chars:
        x = x.replace(p, "")
    return x



positive_words = []
with open("assets/positive_words.txt") as pos_f:
    for lin in pos_f:
        if lin[0] != ';' and lin[0] != '\n':
            positive_words.append(lin.strip())



def get_pos(text):
    cont = 0
    words = strip_punctuation(text.lower()).split()
    for p in words:
        if p in positive_words:
            cont += 1
    return cont


negative_words = []
with open("assets/negative_words.txt") as pos_f:
    for lin in pos_f:
        if lin[0] != ';' and lin[0] != '\n':
            negative_words.append(lin.strip())

def get_neg(text):
    cont = 0
    words = strip_punctuation(text.lower()).split()
    for p in words:
        if p in negative_words:
            cont += 1
    return cont



tekstin = open("project_twitter_data.csv", "r")
tekstout = open("resulting_data.csv", "w")

writer = csv.writer(tekstout)
writer.writerow([
    "Number of Retweets",
    "Number of Replies",
    "Positive Score",
    "Negative Score",
    "Net Score"])
reader = csv.reader(tekstin)
next(reader)

for row in reader:
    tweet_text = row[0]
    retweets = int(row[1])
    replies = int(row[2])

    pos = get_pos(tweet_text)
    neg = get_neg(tweet_text)
    net = pos - neg

    writer.writerow([retweets, replies, pos, neg, net])