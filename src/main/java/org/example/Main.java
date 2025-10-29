package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

//`        new MyThread().start();
//        MyThread myThread = new MyThread();
//        myThread.start();
//        new Thread(new MyRunnable()).start();
//        new Thread(new MyRunnable()).start();

        List<CounterThread> threads = new ArrayList<>();
        SimpleCounter counter = new SimpleCounter();
        for (int i = 0; i < 5; i++) {
            CounterThread counterThread = new CounterThread(i, counter);
            counterThread.start();
            threads.add(counterThread);
        }
        for(CounterThread thread : threads){
            thread.join();
        }
        System.out.println("COUNTER:" + counter.getCount());
    }
}