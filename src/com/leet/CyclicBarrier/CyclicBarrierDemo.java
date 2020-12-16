package com.leet.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void waitting(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("人到齐，开会");
        });

        for(int i = 1;i <= 7;i++){
            int finalI = i;
            new Thread(()->{
                 System.out.println("第"+ finalI +"个人到了");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }


             },String.valueOf(i)).start();
        }

    }
}
