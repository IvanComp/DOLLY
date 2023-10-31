import random
from flask import Flask, jsonify, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

fanStatus = False  # Initial fan status

@app.route('/', methods=['GET'])
def home():
    # Generate HTML links for each endpoint
    links = [
        '<a href="/temperature">Temperature</a>',
        '<a href="/humidity">Humidity</a>',
        '<a href="/wind">Wind</a>',
        '<a href="/rain">Rain</a>',
        '<a href="/uv">UV</a>',
        '<a href="/fan">Fan</a>',
        '<a href="/fan/toggle">Toggle Fan</a>'
    ]

    # Join the links with line breaks
    links_html = '<br>'.join(links)

    # Return the links as the response
    response = f'<h1>Available Endpoints:</h1>{links_html}'
    return response

@app.route('/temperature', methods=['GET'])
def get_temperature():
    temperature = random.uniform(20.0, 30.0)  # Generate a random variable between 20.0 and 30.0
    response = {'sensor': 'temperature', 'value': temperature}
    return jsonify(response)

@app.route('/humidity', methods=['GET'])
def get_humidity():
    humidity = random.uniform(30.0, 90.0)  # Generate a random variable between 20.0 and 30.0
    response = {'sensor': 'humidity', 'value': f'{humidity}%'}
    return jsonify(response)

@app.route('/wind', methods=['GET'])
def get_wind():
    wind_speed = random.uniform(0.0, 100.0)  # Generate a random variable between 20.0 and 30.0
    response = {'sensor': 'temperature', 'value': f'{wind_speed} m/s'}
    return jsonify(response)

@app.route('/rain', methods=['GET'])
def get_rain():
    rain_quantity = random.uniform(0.0, 600.0)  # Generate a random variable between 20.0 and 30.0
    response = {'sensor': 'temperature', 'value': f'{rain_quantity} mm/h'}
    return jsonify(response)

@app.route('/uv', methods=['GET'])
def get_uv():
    uv_index = random.uniform(0.0, 10.0)  # Generate a random variable between 20.0 and 30.0
    response = {'sensor': 'temperature', 'value': uv_index}
    return jsonify(response)

'''FAN'''

@app.route('/fan', methods=['GET'])
def get_fanStatus():
    response = {'actuator': 'fan', 'value': fanStatus}
    return jsonify(response)

@app.route('/fan', methods=['POST'])
def set_fanStatus():
    global fanStatus  # Use the global variable

    # Get the value from the POST request data
    data = request.json
    new_status = data['status']

    # Set the new fan status
    fanStatus = new_status

    response = {'actuator': 'fan', 'value': fanStatus}
    return jsonify(response)

@app.route('/fan/toggle', methods=['GET', 'POST'])
def toggle_fanStatus():
    global fanStatus  # Use the global variable

    # Toggle the fan status between True and False
    fanStatus = not fanStatus

    response = {'actuator': 'fan', 'value': fanStatus}
    return jsonify(response)

if __name__ == '__main__':
    app.run(host='localhost', port=8081)
