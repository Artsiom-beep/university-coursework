from flask import Flask, jsonify

app = Flask(__name__)

@app.route("/", methods=["GET"])
def home():
    return jsonify({"message": "Hello, REST API!"})

@app.route("/hello/<name>", methods=["GET"])
def hello(name):
    return jsonify({"hello": name})

if __name__ == "main":
    app.run(debug=True)