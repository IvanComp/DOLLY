import random
from flask import Flask, jsonify, render_template, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# Variabili per le posizioni delle navi
moli = [
    {'id': 1, 'x': random.uniform(0, 100), 'y': random.uniform(0, 100)},
    {'id': 2, 'x': random.uniform(0, 100), 'y': random.uniform(0, 100)},
    {'id': 3, 'x': random.uniform(0, 100), 'y': random.uniform(0, 100)},
    {'id': 4, 'x': random.uniform(0, 100), 'y': random.uniform(0, 100)},
]

# Variabili per le navi
navi = []


@app.route('/', methods=['GET'])
def home():
    return render_template('index.html')


@app.route('/get_ships', methods=['GET'])
def get_ships():
    return jsonify({'ships': navi})


@app.route('/update_ships', methods=['POST'])
def update_ships():
    global navi
    data = request.json
    navi = data['ships']
    return jsonify({'success': True})


if __name__ == '__main__':
    app.run(host='localhost', port=8083)
