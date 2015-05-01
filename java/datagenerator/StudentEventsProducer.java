package com.hortonworks.tutorials.tutorial1;

/**
 * StudentEventsProducer class simulates the real time student event generation.
 *
 */
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.log4j.Logger;


public class StudentEventsProducer {

    private static final Logger LOG = Logger.getLogger(StudentEventsProducer.class);

    public static void main(String[] args) 
            throws ParserConfigurationException, SAXException, IOException, URISyntaxException 
    {
        if (args.length != 2) 
        {
            
            System.out.println("Usage: StudentEventsProducer <broker list> <zookeeper>");
            System.exit(-1);
        }
        
        LOG.debug("Using broker list:" + args[0] +", zk conn:" + args[1]);
        
        // long events = Long.parseLong(args[0]);
        Random rnd = new Random();
        
        Properties props = new Properties();
        props.put("metadata.broker.list", args[0]);
        props.put("zk.connect", args[1]);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        String TOPIC = "studentevent";
        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

		/*
        String[] events = {"Normal", "Normal", "Normal", "Normal", "Normal", "Normal", "Lane Departure", 
                           "Overspeed", "Normal", "Normal", "Normal", "Normal", "Lane Departure","Normal", 
                           "Normal", "Normal", "Normal",  "Unsafe tail distance", "Normal", "Normal", 
                           "Unsafe following distance", "Normal", "Normal", "Normal","Normal","Normal","Normal",
                           "Normal","Normal","Normal","Normal","Normal","Normal","Normal", "Normal", "Overspeed"
                            ,"Normal", "Normal","Normal","Normal","Normal","Normal","Normal" };
        */
		
		String[] transit_type = {"walk","bike","car","school-bus"};
        Random random = new Random();

        String finalEvent = "";
		String finalEvent2 = "";
        
        String route17 = "route17.kml";
        String[] arrayroute17 = GetKmlLanLangList(route17);
        String route17k = "route17k.kml";
        String[] arrayroute17k = GetKmlLanLangList(route17k);
        String route208 = "route208.kml";
        String[] arrayroute208 = GetKmlLanLangList(route208);
        String route27 = "route27.kml";
        String[] arrayroute27 = GetKmlLanLangList(route27);
        
		String[] studentIds = {"1", "2", "3","4","5","6","7","8","9","10"};
        String[] students = {"student1", "student2", "student3","student4","student5","student6","student7","student8","student9","student10"};
        //String[] routeName = {"route17", "route17k", "route208", "route27"};
        String[] studentAges = {"11", "12", "13", "14","15","16"};
        
        //int evtCnt = events.length;
        
        //Find max route arraysize.
        int maxarraysize = arrayroute17.length;
        if(maxarraysize < arrayroute17k.length)
        	maxarraysize = arrayroute17k.length;
        if(maxarraysize < arrayroute208.length)
        	maxarraysize = arrayroute208.length;
        if(maxarraysize < arrayroute27.length)
        	maxarraysize = arrayroute208.length;
        
        for (int i = 0; i < maxarraysize; i++) 
        {
        	
        	if(arrayroute17.length > i)
        	{
	        	finalEvent = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[0] + "|" + studentAges[2] + "|" + transit_type[3] 
	                    + "|" + getLatLong(arrayroute17[i]);
						
				finalEvent2 = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[4] + "|" + studentAges[5] + "|" + transit_type[1] 
	                    + "|" + getLatLong(arrayroute17[i]);
						
	        	try {
	                KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, finalEvent);
					KeyedMessage<String, String> data2 = new KeyedMessage<String, String>(TOPIC, finalEvent2);
	                LOG.info("Sending Messge #: " + students[0] + ": " + i +", msg:" + finalEvent);
					LOG.info("Sending Messge #: " + students[4] + ": " + i +", msg:" + finalEvent2);
	                producer.send(data);
					producer.send(data2);
	                Thread.sleep(1000);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	}
        	if(arrayroute17k.length > i)
        	{
	        	finalEvent = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[1] + "|" + studentAges[1] + "|" + transit_type[1] 
	                    + "|" + getLatLong(arrayroute17k[i]);
						
				finalEvent2 = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[5] + "|" + studentAges[3] + "|" + transit_type[2] 
	                    + "|" + getLatLong(arrayroute17k[i]);
						
	        	try {
	                KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, finalEvent);
	                LOG.info("Sending Messge #: " + students[1] + ": " + i +", msg:" + finalEvent);
	                producer.send(data);
					KeyedMessage<String, String> data2 = new KeyedMessage<String, String>(TOPIC, finalEvent2);
	                LOG.info("Sending Messge #: " + students[5] + ": " + i +", msg:" + finalEvent2);
	                producer.send(data2);
	                Thread.sleep(1000);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	}
        	if(arrayroute208.length > i)
        	{
	        	finalEvent = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[2] + "|" + studentAges[0] + "|" + transit_type[1]
	                    + "|" + getLatLong(arrayroute208[i]);
						
				finalEvent2 = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[6] + "|" + studentAges[4] + "|" + transit_type[0] 
	                    + "|" + getLatLong(arrayroute208[i]);
						
	        	try {
	                KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, finalEvent);
	                LOG.info("Sending Messge #: " + students[2] + ": " + i +", msg:" + finalEvent);
	                producer.send(data);
					KeyedMessage<String, String> data2 = new KeyedMessage<String, String>(TOPIC, finalEvent2);
	                LOG.info("Sending Messge #: " + students[6] + ": " + i +", msg:" + finalEvent2);
	                producer.send(data2);
	                Thread.sleep(1000);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	}
        	if(arrayroute27.length > i)
        	{
	        	finalEvent = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[3] + "|" + studentAges[2] + "|" + transit_type[3] 
	                    + "|" + getLatLong(arrayroute27[i]);
						
				finalEvent2 = new Timestamp(new Date().getTime()) + "|"
	                    + studentIds[7] + "|" + studentAges[5] + "|" + transit_type[0] 
	                    + "|" + getLatLong(arrayroute27[i]);
						
	        	try {
	                KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC, finalEvent);
	                LOG.info("Sending Messge #: " + students[3] + ": " + i +", msg:" + finalEvent);
	                producer.send(data);
					KeyedMessage<String, String> data2 = new KeyedMessage<String, String>(TOPIC, finalEvent2);
	                LOG.info("Sending Messge #: " + students[7] + ": " + i +", msg:" + finalEvent2);
	                producer.send(data2);
	                Thread.sleep(1000);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
        	}
        }
        
        producer.close();
    }

    private static String getLatLong(String str)
    {
        str=str.replace("\t", "");
        str=str.replace("\n", "");
        
        String[] latLong = str.split("|");
        
        if (latLong.length == 2)
        {
            return latLong[1].trim() + "|" + latLong[0].trim();
        } 
        else 
        {
            return str;    
        }
    }
    
	public static String[] GetKmlLanLangList(String urlString) throws ParserConfigurationException, SAXException, IOException {
        String[] array = null;
        String[] array2 = null;
        
        Document doc = null;
        String pathConent = "";
        File fXmlFile = new File(urlString);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(ClassLoader.getSystemResourceAsStream(urlString));
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("LineString");
        System.out.println(nList.getLength());
        int j=0;
        for (int temp = 0; temp < nList.getLength();temp++) {
        	
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String strLatLon = eElement.getElementsByTagName("coordinates").item(0).getTextContent().toString();
                    array = strLatLon.split(" ");
                    array2 = new String[array.length];
                    for(int i = 0; i< array.length; i++)
                    {
                    	array2[i]=array[i].replace(',' ,'|');
                    }
                                        
                }
            
        }
        return array2;
    }
}