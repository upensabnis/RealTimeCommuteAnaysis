package com.hortonworks.tutorials.tutorial5;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

/**
 *
 * @author Kumar Kandasami
 */
public class StudentEventProcessingTopology extends BaseStudentEventTopology 
{
    private static final String KAFKA_SPOUT_ID = "kafkaSpout"; 
    private static final String LOG_STUDENT_BOLT_ID = "logStudentEventBolt";
            
    public StudentEventProcessingTopology(String configFileLocation) throws Exception 
    {
        super(configFileLocation);
    }

    private SpoutConfig constructKafkaSpoutConf() 
    {
        BrokerHosts hosts = new ZkHosts(topologyConfig.getProperty("kafka.zookeeper.host.port"));
        String topic = topologyConfig.getProperty("kafka.topic");
        String zkRoot = topologyConfig.getProperty("kafka.zkRoot");
        String consumerGroupId = "StormSpout";

        SpoutConfig spoutConfig = new SpoutConfig(hosts, topic, zkRoot, consumerGroupId);

        /* Custom StudentScheme that will take Kafka message of single studentEvent 
         * and emit a 2-tuple consisting of studentId and studentEvent. This studentId
         * is required to do a fieldsSorting so that all student events are sent to the set of bolts */
        spoutConfig.scheme = new SchemeAsMultiScheme(new StudentScheme());

        return spoutConfig;
    }
        
    
    public void configureKafkaSpout(TopologyBuilder builder) 
    {
        KafkaSpout kafkaSpout = new KafkaSpout(constructKafkaSpoutConf());
        int spoutCount = Integer.valueOf(topologyConfig.getProperty("spout.thread.count"));
        builder.setSpout(KAFKA_SPOUT_ID, kafkaSpout);
    }
    
    public void configureLogStudentEventBolt(TopologyBuilder builder)
    {
        LogStudentEventsBolt logBolt = new LogStudentEventsBolt();
        builder.setBolt(LOG_STUDENT_BOLT_ID, logBolt).globalGrouping(KAFKA_SPOUT_ID);
    }
    
		
    private void buildAndSubmit() throws Exception
    {
        TopologyBuilder builder = new TopologyBuilder();
        configureKafkaSpout(builder);
        configureLogStudentEventBolt(builder);
        Config conf = new Config();
	conf.setDebug(true);
        
        StormSubmitter.submitTopology("student-event-processor", 
                                    conf, builder.createTopology());
    }

    public static void main(String[] str) throws Exception
    {
        String configFileLocation = "student_event_topology.properties";
        StudentEventProcessingTopology studentTopology 
                = new StudentEventProcessingTopology(configFileLocation);
        studentTopology.buildAndSubmit();
    }

}
