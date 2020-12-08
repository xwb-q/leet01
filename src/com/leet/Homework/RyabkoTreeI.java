package com.leet.Homework;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class RyabkoTreeI implements PrefixStack {

    int total = 64;

    Integer[] arr = new Integer[total];
    Long[] sums = new Long[total];

    Integer[] updates = new Integer[total];//记录改变得位置和信息
    boolean[] changed = new boolean[total];//记录是否改变
    int size = 0;

    @Override
    public void push(int x) {

        if(size>=total * 0.75)
            arr = resize();

        if(size==0)
            sums[size] = (long)x;
        else
            sums[size] = sums[size-1] + x;
        arr[size++] = x;
    }

    public Integer[] resize(){
        total = total<<1;

        Integer[] newarr = new Integer[total];

        Long[] newsum = new Long[total];

        System.arraycopy(arr,0,newarr,0,arr.length);

        System.arraycopy(sums,0,newsum,0,sums.length);

        sums = newsum;
        return newarr;
    }


    @Override
    public int pop() {

        int value = arr[--size];

        sums[size] = 0L;

        return value;
    }

    @Override
    public int get(int i) {
        return arr[i];
    }

    @Override
    public int set(int i, int x) {
        int old = arr[i];
        arr[i]  =x;
        updates[i] = x - old;
        sums[i] = sums[i] + x - old;
        changed[i] = true;
        return old;
    }

    @Override
    public long prefixSum(int i) {
        long c = 0L;
        int index = 0;
        while (index <= i)
            if(changed[index++]){
                c += updates[index-1];
            }

            return sums[i] + c;
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
