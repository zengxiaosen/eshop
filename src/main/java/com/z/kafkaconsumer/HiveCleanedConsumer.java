package com.z.kafkaconsumer;

/**
 * Created by root on 17-12-5.
 */

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.*;

/**
 * Hive数据清洗消费者
 */
public class HiveCleanedConsumer {
    private final ConsumerConnector consumerConn;
    private final String topic = "eshop";
    private static HDFSWriter writer = new HDFSWriter();

    public HiveCleanedConsumer(){
        Properties props = new Properties();
        props.put("zookeeper.connect", "s202:2181");
        props.put("group.id", "ggg2");
        props.put("auto.offset.reset", "smallest");
        props.put("zookeeper.session.timeout.ms", "500");
        props.put("zookeeper.sync.time.ms", "250");
        props.put("auto.commit.interval.ms", "1000");
        // 创建消费者连接器
        consumerConn = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));

    }
    /**
     * 处理log
     */
    public void processLog(){
        //指定消费的主题
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(1));

        //消费的消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumerConn.createMessageStreams(topicCount);

        //得到指定主题的消息列表
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);

        for(final KafkaStream stream : streams){
            //

        }
    }
}
