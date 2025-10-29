package org.example;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class CounterThread extends Thread {
    private final int threadNum;
    private final SimpleCounter counter;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Thread start num = " + threadNum);
        for (int i = 0; i < 5; i++) {
            counter.inc();
            sleep(100);
        }
        System.out.println("Thread end num = " + threadNum);
    }
}
