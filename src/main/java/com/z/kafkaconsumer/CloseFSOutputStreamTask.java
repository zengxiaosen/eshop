package com.z.kafkaconsumer;

import java.util.TimerTask;

/**
 * Created by root on 17-12-5.
 */
public class CloseFSOutputStreamTask extends TimerTask{
    public void run(){
        HDFSOutputStreamPool pool = HDFSOutputStreamPool.getInstance();
        pool.releasePool();
    }
}
