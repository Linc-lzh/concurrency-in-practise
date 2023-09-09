package com.concurrency.interrupted;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class TimedRun {
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {

        ExecutorService taskExec = newFixedThreadPool(10);
        Future<?> task = taskExec.submit(r);

        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // ...
        } catch (ExecutionException e) {
            // ...
        } finally {
            task.cancel(true);
        }
    }
}
