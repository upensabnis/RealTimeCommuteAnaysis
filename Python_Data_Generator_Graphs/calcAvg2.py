from pymongo import MongoClient
import names
from faker import Factory
import random
import math


faker = Factory.create()
client = MongoClient('localhost', 27017)
db = client.test_database_proj
people=db.people


#Find Average commute time with respect to students in each school
schoolL=['A','B','C','D']
modes=['Bus','Car','Bike','walk']

for i,j in enumerate(schoolL):
	count=0
	avg=0
	totalTimeSpent=0
	print "\n"
	for k,l in enumerate(modes):
		peeps=people.find({'schoolId':schoolL[i]},{'mode':modes[k]})
		for each in peeps:
			print each
			totalTimeSpent+= each['timespent']
			print "foo",totalTimeSpent
			count+=1
		print "The average Timespent in commute by students in School ",j," in ",l," is",totalTimeSpent/count
		totalTimeSpent=0
		count=0  
	