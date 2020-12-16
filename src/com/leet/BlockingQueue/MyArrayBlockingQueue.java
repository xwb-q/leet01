package com.leet.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class MyArrayBlockingQueue {

    public void ArrayBlockingQueuetest(){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue1 = new SynchronousQueue<>();
    }
}
