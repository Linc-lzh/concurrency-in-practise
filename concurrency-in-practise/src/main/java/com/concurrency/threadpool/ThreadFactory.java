package com.concurrency.threadpool;

public interface ThreadFactory {
    Thread newThread(Runnable r);
}
