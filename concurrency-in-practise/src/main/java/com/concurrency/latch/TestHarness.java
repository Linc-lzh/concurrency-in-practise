package com.concurrency.latch;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public static void main(String[] args) throws InterruptedException {
        long result = timeTasks(10, new HarnessTask());
        System.out.println(result);
    }

    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0; i < nThreads; i++){
            Thread t = new Thread(){
                public void run(){
                    try {
                        startGate.await();
                        try{
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignord){

                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
