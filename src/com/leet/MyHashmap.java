package com.leet;

public class MyHashmap<K,V> {

    private int CAPACITY = 8;
    private Entry[] table;

    public MyHashmap() {
        this.table = new Entry[CAPACITY];
    }

    public Object put(K k,V v){
        int hash = k.hashCode();
        int index = hash % 8;
        Entry entry = new Entry(k,v,table[index]);
        table[index] = entry;

        return table;
    }

    class Entry<K,V>{
        private K k;
        private V v;
        private Entry next;

        public Entry(K k, V v, Entry next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
}
