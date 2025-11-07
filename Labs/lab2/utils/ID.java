package lab2.utils;

import java.util.concurrent.atomic.AtomicLong;

public class ID {
    private static final AtomicLong counter = new AtomicLong(0);
    public static long generate() {
        return counter.incrementAndGet();
    }
}