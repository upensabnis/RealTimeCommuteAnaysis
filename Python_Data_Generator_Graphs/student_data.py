from pymongo import MongoClient
import names
from faker import Factory
import random
import math


faker = Factory.create()
client = MongoClient('localhost', 27017)
db = client.test_database_proj
people=db.people
peeps=people.find({'schoolId':'B'})


for i,j in enumerate(peeps):

	if (j==7):
		break
	print j
