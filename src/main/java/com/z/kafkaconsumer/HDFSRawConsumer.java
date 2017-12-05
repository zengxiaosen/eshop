package com.z.kafkaconsumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.hadoop.fs.FSDataOutputStream;

import java.util.*;

/**
 * Created by root on 17-12-5.
 */
public class HDFSRawConsumer {
    private final ConsumerConnector consumerConn;
    private final String topic = "eshop";

    private static HDFSWriter writer = new HDFSWriter();
    public HDFSRawConsumer(){
        Properties props = new Properties();
        props.put("zookeeper.connect", "s202:2181 s203:2181 s204:2181");
        props.put("group.id", "ggg1");
        //from begining consume , cong ling kai shi xiao fei
        props.put("auto.offset.reset", "smallest");
        props.put("zookeeper.session.timeout.ms", "500");
        props.put("zookeeper.sync,time.ms", "250");
        props.put("auto.commit.interval.ms", "1000");
        // 创建消费者连接器
        consumerConn = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));

    }

    /**
     * 处理log
     */
    public void processLog(){
        // 指定消费的主题
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, new Integer(1));
        // 消费的消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams =
                consumerConn.createMessageStreams(topicCount);
        // get 指定主题的消息列表
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);

        for(final KafkaStream stream : streams){
            ConsumerIterator<byte[], byte[]> consumerIte = stream.iterator();
            //迭代日志消息
            MyFSDataOutputStream out = null;
            String prePath = "";
            while(consumerIte.hasNext()){
                byte[] msg = consumerIte.next().message();
                String log = new String(msg);
                String[] arr =
            }
        }
    }


}
