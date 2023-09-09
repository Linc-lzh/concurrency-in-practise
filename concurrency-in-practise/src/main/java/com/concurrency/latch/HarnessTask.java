package com.concurrency.latch;

public class HarnessTask implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start to run...");
    }
}
