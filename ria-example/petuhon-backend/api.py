from flask import Flask, jsonify, request
from domain import Transaction

app = Flask(__name__)
transactionsList = [Transaction('Andrew', 'Test tx', 1000, 'OK', 123123123344),
				Transaction('Mike', 'For my beer', 200, 'OK', 23123123123),
				Transaction('George', 'Happy birthday', 500, 'PENDING', 123123133445),
				Transaction('Michael', 'My debt', 5000, 'FAILED', 21423543424)]

@app.route('/transactions', methods=['GET'])
def transactions():
    return jsonify(txs=[vars(tx) for tx in transactionsList])

@app.route('/transactions', methods=['POST'])
def save_transaction():
	return ''

if __name__ == "__main__":
    app.run(debug=True)