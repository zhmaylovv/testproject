package org.example;

import lombok.SneakyThrows;

public class MyRunnable implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Поток runnable #" + Thread.currentThread().getName() + ", счетчик: " + i);
        }
    }
}
