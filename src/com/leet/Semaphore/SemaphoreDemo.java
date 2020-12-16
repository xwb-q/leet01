package com.leet.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void Semaphoretest(){
        Semaphore semaphore = new Semaphore(3,true);

        for(int i = 1;i <= 6;i++){
             new Thread(()->{
                 try {
                     semaphore.acquire();
                     System.out.println(Thread.currentThread().getName()+"\t 号 客 人 进入了位置");
                     try {
                         TimeUnit.SECONDS.sleep(3);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("3秒后"+Thread.currentThread().getName()+"号 客 人 吃完了火锅，离开");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }finally {
                     semaphore.release();
                 }
             },String.valueOf(i)).start();
        }
    }


}
