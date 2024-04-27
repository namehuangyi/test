package com.powernode.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class MyThreadlocal<T> {
    private Map<Thread, T> map = new HashMap<>();

    public void set (T obj){
        map.put(Thread.currentThread(), obj);
    }

    public T get(){
        return map.get(Thread.currentThread());
    }

    public void move(){
        map.remove(Thread.currentThread());
    }
}
