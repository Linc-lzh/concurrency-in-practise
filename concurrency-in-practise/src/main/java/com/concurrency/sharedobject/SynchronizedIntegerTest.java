package com.concurrency.sharedobject;

public class SynchronizedIntegerTest {
    public static void main(String[] args) {
        SynchronizedInteger integer = new SynchronizedInteger();
        synchronized (integer) {
            integer.set(50);
            System.out.println(integer.get());

            integer.set(100);
            System.out.println(integer.get());
        }
    }
}
