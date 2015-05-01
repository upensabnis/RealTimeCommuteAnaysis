from pymongo import MongoClient
import names
from faker import Factory
import random

faker = Factory.create()
client = MongoClient('localhost', 27017)
db = client.test_database_proj
people=db.people
#people.insert({'name':'mike','food':'cheese'})
#people.insert({'name':'john','food':'ham','location':'uk'})
#people.insert({'name':'john','food':'pizza','location':'uk'})
#people.insert({'name':'kelly','food':'pizza','location':'uk'})
#peeps=people.find({'name':'mikel'}) # select * from tablename
#peeps=people.find({'name':'kelly'}) # select * from tablename

# INSERT NAMES FOR STUDENTS IN SCHOOL A
SchoolA_students=40000
SchoolB_students=30000
SchoolC_students=20000
SchoolD_students=25000
count=0
coord1=0
coord2=10000000
modes=['Bus','Car','Bike','walk']
speed=[7,13,10,1]
#SCHOOL A:	INSERT SID, SCHOOLID


def velocity(m):

	if(m=='Bus'):
		return speed[0]
	elif (m=='Car'):
		return speed[1]
	elif (m=='Bike'):
		return speed[2]
	else:
		return speed[3]





for i in range(SchoolA_students):
	print "A",count
	mode=random.choice(modes)
	vel=velocity(mode)

	people.insert({'SID':count,'StudentName':names.get_full_name(),'StudentAddress':faker.address()
,'schoolId':'A','homeCordx':random.randint(coord1,coord2),'homeCordy':random.randint(coord1,coord2),'schoolAddressx':'5000000','schoolAddressy':'5000000','mode':mode,'vel':vel})
	count+=1

#SCHOOL B:	INSERT SID, SCHOOLID
for i in range(count+1,count+SchoolB_students+1):
	print "B",count
	mode=random.choice(modes)
	vel=velocity(mode)
	people.insert({'SID':count,'StudentName':names.get_full_name(),'StudentAddress':faker.address()
,'schoolId':'B','homeCordx':random.randint(coord1,coord2),'homeCordy':random.randint(coord1,coord2),'schoolAddressx':'8000000','schoolAddressy':'0','mode':mode,'vel':vel})
	count+=1


#SCHOOL C:	INSERT SID, SCHOOLID
for i in range(count+1,count+SchoolC_students+1):
	print "C",count
	mode=random.choice(modes)
	vel=velocity(mode)
	people.insert({'SID':count,'StudentName':names.get_full_name(),'StudentAddress':faker.address()
,'schoolId':'C','homeCordx':random.randint(coord1,coord2),'homeCordy':random.randint(coord1,coord2),'schoolAddressx':'10000000','schoolAddressy':'10000000','mode':mode,'vel':vel})
	count+=1

#SCHOOL D:	INSERT SID, SCHOOLID
for i in range(count+1,count+SchoolD_students+1):
	print "D",count
	mode=random.choice(modes)
	vel=velocity(mode)
	people.insert({'SID':count,'StudentName':names.get_full_name(),'StudentAddress':faker.address()
,'schoolId':'D','homeCordx':random.randint(coord1,coord2),'homeCordy':random.randint(coord1,coord2),'schoolAddressx':'2000000','schoolAddressy':'8000000','mode':mode,'vel':vel})
	count+=1


peeps=people.find()


for each in peeps:
	pass
	#print each





#people.remove(each)






'''
import threading


def worker(num):
	
    print 'Worker: %s' % num
    return

threads = []
for i in range(5):
    t = threading.Thread(target=worker, args=(i,))
    threads.append(t)
    t.start()
'''




'''
try:
	peeps=people.find_one({'name':'mike'})
	people.remove(peeps)
except:
	pass
'''

#peeps=people.find_one({'name':'john'})
#people.remove(peeps)




'''
import threading

def worker():
    """thread worker function"""
    print 'Worker'
    
def worker2():
	print "this is worker 2"

threads = []

t = threading.Thread(target=worker)
print t
t2=threading.Thread(target=worker2)
threads.append(t)
threads.append(t2)
t.start()
t2.start()
'''