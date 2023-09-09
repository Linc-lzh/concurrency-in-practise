package com.concurrency.blockingqueue;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IndexTest {
    public static void main(String[] args) {

        File[] roots = null;
        startIndexing(roots);
    }

    public static void startIndexing(File[] roots){
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(1000);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for(File root : roots){
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for(int i = 0; i < 1000; i++){
            new Thread(new Indexer(queue)).start();
        }
    }
}
