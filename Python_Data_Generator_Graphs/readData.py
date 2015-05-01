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

CT_A_BUS=0
CT_A_BUS_COUNT=0#

CT_B_BUS=0
CT_B_BUS_COUNT=0#

CT_C_BUS=0
CT_C_BUS_COUNT=0#

CT_D_BUS=0
CT_D_BUS_COUNT=0#

CT_A_CAR=0
CT_A_CAR_COUNT=0#

CT_B_CAR=0
CT_B_CAR_COUNT=0#

CT_C_CAR=0
CT_C_CAR_COUNT=0#

CT_D_CAR=0
CT_D_CAR_COUNT=0#

CT_A_BIKE=0
CT_A_BIKE_COUNT=0#

CT_B_BIKE=0
CT_B_BIKE_COUNT=0#

CT_C_BIKE=0
CT_C_BIKE_COUNT=0#

CT_D_BIKE=0
CT_D_BIKE_COUNT=0#

CT_A_WALK=0
CT_A_WALK_COUNT=0#

CT_B_WALK=0
CT_B_WALK_COUNT=0#

CT_C_WALK=0
CT_C_WALK_COUNT=0#

CT_D_WALK=0
CT_D_WALK_COUNT=0#
for each in peeps:
	if(each['schoolId']=='A'):
		if(each['mode']=='Bus'):
			CT_A_BUS+=float(each['timespent'])
			CT_A_BUS_COUNT+=1
		elif(each['mode']=='Car'):
			CT_A_CAR_COUNT+=1
			CT_A_CAR+=float(each['timespent'])
		elif(each['mode']=='Bike'):
			CT_A_BIKE_COUNT+=1
			CT_A_BIKE+=float(each['timespent'])
		else:
			CT_A_WALK_COUNT+=1
			CT_A_WALK+=float(each['timespent'])
	elif(each['schoolId']=='B'):
		if(each['mode']=='Bus'):
			CT_B_BUS_COUNT+=1
			CT_B_BUS+=float(each['timespent'])
		elif(each['mode']=='Car'):
			CT_B_CAR_COUNT+=1
			CT_B_CAR+=float(each['timespent'])
		elif(each['mode']=='Bike'):
			CT_B_BIKE_COUNT+=1
			CT_B_BIKE+=float(each['timespent'])
		else:
			CT_B_WALK_COUNT+=1
			CT_B_WALK+=float(each['timespent'])
	elif(each['schoolId']=='C'):
		if(each['mode']=='Bus'):
			CT_C_BUS_COUNT+=1
			CT_C_BUS+=float(each['timespent'])
		elif(each['mode']=='Car'):
			CT_C_CAR_COUNT+=1
			CT_C_CAR+=float(each['timespent'])
		elif(each['mode']=='Bike'):
			CT_C_BIKE_COUNT+=1
			CT_C_BIKE+=float(each['timespent'])
		else:
			CT_C_WALK_COUNT+=1
			CT_C_WALK+=float(each['timespent'])
	else:
		if(each['mode']=='Bus'):
			CT_D_BUS_COUNT+=1
			CT_D_BUS+=float(each['timespent'])
		elif(each['mode']=='Car'):
			CT_D_CAR_COUNT+=1
			CT_D_CAR+=float(each['timespent'])
		elif(each['mode']=='Bike'):
			CT_D_BIKE_COUNT+=1
			CT_D_BIKE+=float(each['timespent'])
		else:
			CT_D_WALK_COUNT+=1
			CT_D_WALK+=float(each['timespent'])


print "timespent in Commute for students in School A:"
print "Bus: ",CT_A_BUS/CT_A_BUS_COUNT
print "Car: ",CT_A_CAR/CT_A_CAR_COUNT
print "Bike",CT_A_BIKE/CT_A_BIKE_COUNT
print "Walk",CT_A_WALK/CT_A_WALK_COUNT
print "\n\n"

print "timespent in Commute for students in School B:"
print "Bus: ",CT_B_BUS/CT_B_BUS_COUNT
print "Car: ",CT_B_CAR/CT_B_CAR_COUNT
print "Bike",CT_B_BIKE/CT_B_BIKE_COUNT
print "Walk",CT_B_WALK/CT_B_WALK_COUNT
print "\n\n"

print "timespent in Commute for students in School C:"
print "Bus: ",CT_C_BUS/CT_C_BUS_COUNT
print "Car: ",CT_C_CAR/CT_C_CAR_COUNT
print "Bike",CT_C_BIKE/CT_C_BIKE_COUNT
print "Walk",CT_C_WALK/CT_C_WALK_COUNT
print "\n\n"

print "timespent in Commute for students in School D:"
print "Bus: ",CT_D_BUS/CT_D_BUS_COUNT
print "Car: ",CT_D_CAR/CT_D_CAR_COUNT
print "Bike",CT_D_BIKE/CT_D_BIKE_COUNT
print "Walk",CT_D_WALK/CT_D_WALK_COUNT
print "\n\n"


	


