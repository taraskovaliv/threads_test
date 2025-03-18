package dev.kovaliv.utils;

import java.util.Random;

public class DataUtils {
    public static long[] generateNumbers(int size) {
        return new Random().longs(size).toArray();
    }
}
