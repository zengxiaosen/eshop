package com.z.kafkaconsumer;
import org.apache.hadoop.fs.FSDataOutputStream;
/**
 * Created by root on 17-12-5.
 * HDFS写入器
 */
public class HDFSWriter {
    /**
     * 写入log到hdfs文件
     * hdfs://mycluster/eshop/2017/02/28/s201.log | s202.log | s203.log
     */
    public void writeLog2HDFS(String path, byte[] log){
        //得到我们的装饰流
        FSDataOutputStream out = HDFSOutputStreamPool.getInstance();
    }
}
