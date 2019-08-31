'''
Team ApacheHelicopters - Shin Bamba, Daniel Gelfand, Mohammed Jamil, Ricky Lin
SoftDev2 pd6
K #08 -- Ay Mon, Go Git It From Yer Flask
2019-03-08
'''
from flask import Flask,render_template,request,session
import mongo
import pymongo
import json
import os
app = Flask(__name__) #create instance of class Flask
app.secret_key = os.urandom(32)
collection=None
db=None
@app.route("/") #assign fxn to route
def ip_input():
    return render_template("home.html")
@app.route("/query")
def query():
    ip=request.args.get("ip")
    session["ip"]=ip
    connection=pymongo.MongoClient(ip)
    connection.drop_database("ApacheHelicopters")
    db=connection["ApacheHelicopters"] 
    collection=db["nobel_laureates"] 
    F=open('laureate.json')
    data=json.load(F)
    collection.insert_many(data)

    return render_template("query.html",ip=ip)
def categoryFinder(category,ip):
    connection=pymongo.MongoClient(ip)
    db=connection["ApacheHelicopters"] 
    collection=db["nobel_laureates"] 
    docs=collection.find({"prizes.category":category})
    laureates=[]
    for doc in docs:
        laureates.append(doc)
    return laureates
def yearFinder(year,ip):
    connection=pymongo.MongoClient(ip)
    db=connection["ApacheHelicopters"] 
    collection=db["nobel_laureates"] 
    docs=collection.find({"prizes.year":year})
    laureates=[]
    for doc in docs:
        laureates.append(doc)
    return laureates

@app.route("/results")
def results():
    ip=session.get("ip",None)
    results=[]
    if "category" in request.args:
        query=request.args.get("category")
        results=categoryFinder(query,ip)
    elif "year" in request.args:
        query=request.args.get("year")
        results=yearFinder(query,ip)
    return render_template("results.html",results=results[:50])

app.debug = True
app.run()
