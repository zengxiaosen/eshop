package com.z.kafkaconsumer;

import java.util.Timer;

/**
 * Created by root on 17-12-5.
 */
public class ConsumerApp {
    public static void main(String[] args){
        //开启定时器任务，周期性关闭流
        new Timer().schedule(new CloseFSOutputStreamTask(), 0, 30000);
        //hdfs消费者
        new Thread(){
            public void run(){
                HDFSRawConsumer consumer = new HDFSRawConsumer();
                consumer.processLog();
            }
        }.start();

        //hive清洗消费者
        new Thread(){
            public void run(){
                HiveCleanedConsumer consumer = new HiveCleanedConsumer();
                consumer.processLog();
            }
        }.start();
    }
}
