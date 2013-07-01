from flask import Flask, jsonify, request
from domain import Transaction
import json

app = Flask(__name__)
transactions_list = [Transaction('Andrew', 'Test tx', 1000, 'SUCCEEDED', 123123123344),
				Transaction('Mike', 'For my beer', 200, 'SUCCEEDED', 23123123123),
				Transaction('George', 'Happy birthday', 500, 'PENDING', 123123133445),
				Transaction('Michael', 'My debt', 5000, 'FAILED', 21423543424)]

@app.route('/transactions', methods=['GET'])
def transactions():
    return jsonify(transactions=[vars(tx) for tx in transactions_list])

@app.route('/transactions', methods=['POST'])
def save_transaction():
	new_tx = Transaction(**json.loads(request.data))	
	transactions_list.append(new_tx)

if __name__ == "__main__":
    app.run(debug=True)