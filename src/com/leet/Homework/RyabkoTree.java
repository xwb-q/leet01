package com.leet.Homework;

import java.util.*;

public class RyabkoTree implements PrefixStack {

    int total = 64;
    Integer[] arr = new Integer[total];
    long[] sum = new long[total];
    HashMap<Integer,Integer> update = new HashMap<>();
    ArrayList<Integer> keys = new ArrayList<>();
    int size = 0;



    @Override
    public void push(int x) {
        if(size>=total * 0.75)
            arr = resize();
        if(size==0)
            sum[size] = x;
        else
            sum[size] = sum[size-1] + x;
        arr[size++] = x;
    }

    public Integer[] resize(){
        total = total<<1;

        Integer[] newarr = new Integer[total];

        long[] newsum = new long[total];

        System.arraycopy(arr,0,newarr,0,arr.length);

        System.arraycopy(sum,0,newsum,0,sum.length);

        sum = newsum;
        return newarr;
    }

    @Override
    public int pop() {
        int value = arr[--size];
        sum[size] = 0;
        return value;

    }

    @Override
    public int get(int i) {
        return arr[i];
    }

    @Override
    public int set(int i, int x) {

        if(!update.containsKey(i))
            keys.add(i);

        int old = arr[i];
        arr[i] = x;
        update.put(i,x);


        return old;
    }

    @Override
    public long prefixSum(int i) {

        Set<Integer> keys = update.keySet();
        Integer[] items = new Integer[keys.size()];

        keys.toArray(items);
       Arrays.sort(items);
       long num = 0;
       long prenum = 0;
       for(int item:items){
           if(item>i)
               break;
           num += update.get(item);
           prenum += arr[item];
       }

        return sum[i] + num - prenum;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.asList(arr).iterator();
    }
}
