package com.leet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Reflect {
    public static void reflect() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class c = Class.forName("com.leet.Custom");//得到一个Reflect类
        Method method = c.getDeclaredMethod("login",String.class,String.class);//从c对象中得到一个方法
        Object  o = c.newInstance();//得到一个Custom对象

        Object reflectVal = method.invoke("o","aa","123");//调用某个类的
        System.out.println(reflectVal);
    }
}


