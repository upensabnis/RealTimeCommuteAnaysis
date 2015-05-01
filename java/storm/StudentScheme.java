package com.hortonworks.tutorials.tutorial5;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class StudentScheme implements Scheme 
{

        public static final String FIELD_STUDENT_ID  = "studentId";
        public static final String FIELD_STUDENT_AGE   = "studentAge";
        public static final String FIELD_EVENT_TIME = "eventTime";
        public static final String FIELD_EVENT_TYPE = "eventType";
        public static final String FIELD_LONGITUDE  = "longitude";
        public static final String FIELD_LATITUDE   = "latitude";
        
        public static final String FIELD_INCIDENT_CNT = "incidentCnt";
        
        
	private static final long serialVersionUID = -2990121166902741545L;

	private static final Logger LOG = Logger.getLogger(StudentScheme.class);
	
        /**
         * <timestamp>|studentid|studentage|eventType|long|lat
         * @param bytes
         * @return 
         */
	@Override
	public List<Object> deserialize(byte[] bytes)
        {
		try 
                {
			String studentEvent = new String(bytes, "UTF-8");
			String[] pieces = studentEvent.split("\\|");
			
			Timestamp eventTime = Timestamp.valueOf(pieces[0]);
			String studentAge = pieces[1];
			String studentId = pieces[2];
			String eventType = pieces[3];
			String longitude= pieces[4];
			String latitude  = pieces[5];
			return new Values(cleanup(studentId), cleanup(studentAge), 
                                    eventTime, cleanup(eventType), cleanup(longitude), cleanup(latitude));
			
		} 
                catch (UnsupportedEncodingException e) 
                {
                    LOG.error(e);
                    throw new RuntimeException(e);
		}
		
	}
        
	@Override
	public Fields getOutputFields()
        {
            return new Fields(FIELD_STUDENT_ID,
                              FIELD_STUDENT_AGE, 
                             FIELD_EVENT_TIME, 
                             FIELD_EVENT_TYPE, 
                             FIELD_LONGITUDE, 
                             FIELD_LATITUDE);
		
	}
        
        private String cleanup(String str)
        {
            if (str != null)
            {
                return str.trim().replace("\n", "").replace("\t", "");
            } 
            else
            {
                return str;
            }
            
        }
}