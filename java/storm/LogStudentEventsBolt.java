package com.hortonworks.tutorials.tutorial5;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import java.util.Map;
import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

/**
 *
 * @author Kumar Kandasami
 */
public class LogStudentEventsBolt extends BaseRichBolt
{
    private static final Logger LOG = Logger.getLogger(LogStudentEventsBolt.class);
    private static final MongoClient mongoClient = null;
	private static final DB db = null;
	private static final DBCollection table = null;
	
	public void LogStudentEventsBolt() {
		mongoClient = new MongoClient( "localhost" , 27017 );
		db = mongoClient.getDB( "student" );
		table = db.getCollection("student_coordinates");
	}
	
    public void declareOutputFields(OutputFieldsDeclarer ofd) 
    {
       //none prints to the Logger.
    }

    public void prepare(Map map, TopologyContext tc, OutputCollector oc) 
    {
       //no output.
    }

    public void execute(Tuple tuple) 
    {
		LOG.info(tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID)  + "," + 
              tuple.getStringByField(StudentScheme.FIELD_STUDENT_AGE)    + "," +
              tuple.getValueByField(StudentScheme.FIELD_EVENT_TIME)  + "," +
              tuple.getStringByField(StudentScheme.FIELD_EVENT_TYPE)  + "," +
              tuple.getStringByField(StudentScheme.FIELD_LATITUDE)    + "," +
              tuple.getStringByField(StudentScheme.FIELD_LONGITUDE));

        
		BasicDBObject document = new BasicDBObject("title","student"+tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("studentId",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("studentAge",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("eventTime",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("eventType",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("latitude",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		document.put("longitude",tuple.getStringByField(StudentScheme.FIELD_STUDENT_ID));
		table.insert(document);
    }
    
}
