package org.example;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class SimpleCounter {
//    private static final Object obj = new Object();
    private volatile AtomicInteger count;


    public SimpleCounter() {
        this.count = new AtomicInteger();
    }

    public void inc() {
        count.incrementAndGet();
    }

//    public void inc() {
//        synchronized (obj) {
//            count++;
//        }
//    }
//
//    public void inc() {
//        synchronized (this) {
//            count++;
//        }
//    }
}
