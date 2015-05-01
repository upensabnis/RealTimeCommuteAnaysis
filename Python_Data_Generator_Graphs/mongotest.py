from pymongo import MongoClient
import names
from faker import Factory
import random
import math


faker = Factory.create()
client = MongoClient('localhost', 27017)
db = client.test_database_proj
people=db.people
peeps=people.find()


def distanceReached(s):
	x1=s['homeCordx']
	y1=s['homeCordy']
	x2=s['schoolAddressx']
	y2=s['schoolAddressy']
	dist = math.hypot(int(x2)-int(x1), int(y2)-int(y1))
	print dist
	velocity=s['vel']
	velocity=int(velocity)
	if(velocity==0):
		velocity=1 # to eliminate division by 0 error
	timespent=dist/velocity
	temp=[]
	temp.append(dist)
	temp.append(timespent)
	return temp






for each in peeps:
	#print each
	t=[]
	t=distanceReached(each)
	db.people.update({'_id' : each['_id']},{'$set' : {'distance' :t[0]  }})
	db.people.update({'_id' : each['_id']},{'$set' : {'timespent' :t[1]  }})
	t=[]


'''{u'schoolId': u'A', u'StudentAddress': u'Unit 4343 Box 6877\nDPO AA 69442', u'_id': ObjectId('5541bcd0fc1eec0ff725260a'), u'StudentName': u'Joanna Janusz', 
u'mode': u'Car', u'SID': 1, u'vel': 13, u'schoolAddressy': u'500', u'schoolAddressx': u'500', u'homeCordx': 705, u'homeCordy': 377}
'''
'''
{u'distance': 239.06902768865731, u'schoolId': u'A', u'StudentAddress': u'Unit 4343 Box 6877\nDPO AA 69442', u'_id': ObjectId('5541bcd0fc1eec0ff725260a'), 
u'StudentName': u'Joanna Janusz', u'timespent': 18.389925206819793, u'mode': u'Car', u'SID': 1, u'vel': 13, u'schoolAddressy': u'500', u'schoolAddressx': u'500', 
u'homeCordx': 705, u'homeCordy': 377}
'''
