package com.leet.ReadWriteLockDemo;


import java.util.HashMap;

//class myCache{
//    private HashMap<String,Object> hashMap = new HashMap<>();
//
//    public void put(String key,Object value){
//        System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);
//        hashMap.put(key,value);
//        System.out.println(Thread.currentThread().getName()+"\t 写入完成:");
//    }
//
//    public void get(String key){
//        System.out.println(Thread.currentThread().getName()+"\t 开始读取数据:");
//        Object res = hashMap.get(key);
//        System.out.println(Thread.currentThread().getName()+"\t 读取数据完成:"+res);
//    }
//}

/**
 * @author xwb
 * @create 2020-12-8
 */
public class ReadWriteLockDemo {
//    public static void main(String[] args) {
//        myCache m = new myCache();
//
//        for(int i = 1;i<=5 ;i++){
//            final int finalI = i;
//
//            new Thread(()->{
//                m.put(finalI +"", finalI +"");
//            }).start();
//
//        }
////--------------------------------------------
//        for(int i = 1;i<=5 ;i++){
//            final int finalI = i;
//
//            new Thread(()->{
//                m.get(finalI+"");
//            }).start();
//
//        }
//
//    }

}
