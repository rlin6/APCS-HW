'''
Duckies - Daniel Gelfand and Ricky Lin
SoftDev1 pd6
K#15 -- Oh yes, perhaps I do...
2018-10-03
'''
from flask import Flask, render_template, session, request, url_for, redirect, flash
import os

app = Flask(__name__)
#generate random string
app.secret_key = os.urandom(32)

@app.route("/")
def home():
    #if user is in a session go to welcome page
    if request.cookies.get('session'):
        return render_template('log.html')
    #otherwise be at login page
    else:
        return render_template("index.html")

@app.route("/auth", methods=['GET'])
def login():
    #obtain inputted user and password
    user = request.args['user']
    pw = request.args['pw']
    #check if username and password match up
    if (user == 'Bob'):
        if (pw == 'Duck'):
            #adding data to session
            session['Bob'] = 'Duck'
            return render_template("log.html")
        else:
            flash("Wrong password")
    else:
            flash("Username not found")

    return render_template('index.html')


@app.route("/logout")
def logout():
    #remove data from session
    try:
        session.pop('Bob')
    except:
        pass
    #redirect to root
    return redirect(url_for('home'))


if __name__ == '__main__':
    app.run(debug=True)
