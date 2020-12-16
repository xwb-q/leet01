package com.leet.Homework;

import java.util.*;

public class RyabkoTreeII implements PrefixStack {

    int total = 126;
    long[] c = new long[total];
    Integer[] arr = new Integer[total];
    int size = 0;

    @Override
    public void push(int x) {

        if(size>=total*0.75)
            resize();

        arr[size+1] = x;
        updata(size+1,x);
        size++;

    }

    public void resize(){
        total = total * 2;
        long[] newc = new long[total];
        Integer[] newarr = new Integer[total];

        System.arraycopy(arr,0,newarr,0,arr.length);
        System.arraycopy(c,0,newc,0,c.length);

        c = newc;
        arr = newarr;

        for(int i = 1;i < total;i++){
            updata(i,0);
        }

    }


    @Override
    public int pop() {
        int old = arr[size];
        updata(size,-arr[size]);
        size--;

        return old;

    }

    @Override
    public int get(int i) {
        return arr[i+1];
    }

    public int lowbit(int x){
        return x&(-x);
    }

    @Override
    public int set(int i, int x) {
        i++;
        int old = arr[i];
        arr[i] = x;

        updata(i,x - old);

        return old;
    }

    public void updata(int i,int k){
        while (i<total){
            c[i] += k;
            i += lowbit(i);
        }
    }


    @Override
    public long prefixSum(int i) {  //  0--i 的和

        return getsum(i+1);///   1  ---  i+1  的和
    }

    public int getsum(int i){        //求A[1 - i]的和
        int res = 0;
        while(i > 0){
            res += c[i];
            i -= lowbit(i);
        }
        return res;
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
