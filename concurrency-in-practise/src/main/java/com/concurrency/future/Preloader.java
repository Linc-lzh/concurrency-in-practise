package com.concurrency.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return get();
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        }
        catch (ExecutionException | InterruptedException e){
            Throwable cause = e.getCause();
            if(cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            else
                throw launderThrowable(cause);
        }
    }

    private RuntimeException launderThrowable(Throwable t) {
        if(t instanceof RuntimeException)
            return (RuntimeException) t;
        else if(t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }
}
