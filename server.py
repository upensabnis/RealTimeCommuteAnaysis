from flask import Flask
from flask import render_template
from pymongo import MongoClient
import json
from bson import json_util
from bson.json_util import dumps

app = Flask(__name__)


Mongo_Host = 'localhost'
Mongo_Port = 27017
Dbs_Name = 'twitter'
collection_name = 'test2'
FIELDS = {'firstName':True,'lastName':True}




@app.route('/')
def index():
	return 'This is index'
	
#This will retreive the data from the server and Mongodb and store it in to list. 
@app.route('/data')
def Data():
	client = MongoClient("localhost", 27017)
	db = client.test_database
	collection = db.posts
	data1 = []
	json_data=[]
	data = collection.find()	
	for record in data:
		data1.append(record)
	json_data = json.dumps(data1,default= json_util.default)
	return json_data


if __name__ == '__main__':
	app.debug = True
	app.run()
