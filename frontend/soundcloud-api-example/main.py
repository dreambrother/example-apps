from flask import Flask, redirect, url_for

app = Flask(__name__)
app.debug = True

@app.route("/")
def root():
    return redirect(url_for("static", filename="streaming.html"))

app.run()
