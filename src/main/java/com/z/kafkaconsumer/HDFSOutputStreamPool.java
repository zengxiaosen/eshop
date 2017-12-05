package com.z.kafkaconsumer;

/**
 * Created by root on 17-12-5.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 输出流池
 */
public class HDFSOutputStreamPool {
    private FileSystem fs;
    //存放的所有的输出流
    private Map<String, FSDataOutputStream> pool = new HashMap<String, FSDataOutputStream>();

    private static HDFSOutputStreamPool instance;

    private HDFSOutputStreamPool(){
        try{
            Configuration conf = new Configuration();
            fs = FileSystem.get(conf);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HDFSOutputStreamPool getInstance(){
        if(instance == null){
            instance = new HDFSOutputStreamPool();
        }
        return instance;
    }

    /**
     * 通过路径得到对应的输出流
     */
    public synchronized FSDataOutputStream takeOutputStream(String path){
        try{
            FSDataOutputStream out = pool.remove(path);
            if(out == null){
                Path p = new Path(path);
                if(!fs.exists(p)){
                    fs.createNewFile(p);
                }
                out = fs.append(p);
            }
            return new MyFSDataOutputStream(path, out, this);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 回收流
     */
    public synchronized void putBack(String path, FSDataOutputStream out){
        pool.put(path, out);
    }

    /**
     * 释放池子
     */
    public synchronized void releasePool(){
        try{
            for(FSDataOutputStream o : pool.values()){
                o.close();
            }
            pool.clear();
            System.out.println("池子释放了!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
