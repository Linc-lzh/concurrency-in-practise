package com.concurrency.monitor;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Point {
    public final int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
