package org.example;

import lombok.SneakyThrows;

public class MyThread extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            sleep(100);
            System.out.println("Поток #" + Thread.currentThread().getName() + ", счетчик: " + i);
        }
    }
}
