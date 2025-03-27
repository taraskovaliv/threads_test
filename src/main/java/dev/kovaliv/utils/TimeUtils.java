package dev.kovaliv.utils;

public class TimeUtils {
    public static long measureTime(Runnable runnable) {
        long time = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            runnable.run();
            long tmpTime = System.nanoTime() - startTime;
            if (tmpTime < time) {
                time = tmpTime;
            }
        }
        return time;
    }
}
