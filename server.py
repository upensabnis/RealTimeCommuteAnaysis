from flask import Flask
from flask import render_template
from flask import url_for
from pymongo import MongoClient
import json
from bson import json_util
from bson.json_util import dumps

app = Flask(__name__)

@app.route('/')
def index():
	return render_template("Data.html");
	
#This will retreive the data from the server and Mongodb and store it in to list. 
@app.route('/data')
def data():
	client = MongoClient("localhost", 27017)
	db = client.student
	collection = db.posts
	json_data=[]
	data = collection.find()	
	for record in data:
		json_data.append(record)
	json_data = json.dumps(json_data,default= json_util.default)
	return json_data
	
if __name__ == '__main__':
	app.debug = True
	app.run()
