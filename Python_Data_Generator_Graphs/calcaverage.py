from pymongo import MongoClient
import names
from faker import Factory
import random
import math


faker = Factory.create()
client = MongoClient('localhost', 27017)
db = client.test_database_proj
people=db.people


#Find average Commute time of students
peeps=people.find()
count=0
avg=0
totalTimeSpent=0
for each in peeps:
	totalTimeSpent+= each['timespent']
	count+=1
avg=totalTimeSpent/count
print "The average time spent in commute of the students of boulder",avg


#Find Average commute time with respect to students in each school
schoolL=['A','B','C','D']
for i,j in enumerate(schoolL):
	peeps=people.find({'schoolId':schoolL[i]})
	count=0
	avg=0
	totalTimeSpent=0

	for each in peeps:
		totalTimeSpent+= each['timespent']
		#print "inner loop",totalTimeSpent
		count+=1
	print "The average time spent in commute of the students of in School ",j," is ", totalTimeSpent/count
	
