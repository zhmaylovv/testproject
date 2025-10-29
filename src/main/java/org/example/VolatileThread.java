package org.example;

import lombok.SneakyThrows;

import javax.sound.midi.Soundbank;

public class VolatileThread {
    private static volatile boolean RUNNING = true;

    @SneakyThrows
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            while (RUNNING) {
                int x = 0;
                for (int i = 0; i < 1000; i++) {
                    x++;
                }
            }
            System.out.println("Worker ended");
        });
        worker.start();
        Thread.sleep(2000);
        RUNNING = false;
        worker.join();
    }
}
