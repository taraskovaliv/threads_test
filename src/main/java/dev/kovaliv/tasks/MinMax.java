package dev.kovaliv.tasks;

import dev.kovaliv.data.Max;
import dev.kovaliv.data.Min;
import dev.kovaliv.data.Result;
import dev.kovaliv.utils.DataUtils;
import dev.kovaliv.utils.UrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static dev.kovaliv.utils.TimeUtils.measureTime;
import static dev.kovaliv.utils.UrlUtils.BASE_URL;
import static dev.kovaliv.utils.UrlUtils.getRequest;
import static java.lang.Math.min;

public class MinMax implements Task {

    @Override
    public String getName() {
        return "MinMax";
    }

    @Override
    public List<Result> run(int maxThreads) {
        List<Result> results = new ArrayList<>(maxThreads * 6);
        for (int i = 10_000; i <= 1_000_000_000; i *= 10) {
            long[] numbers = DataUtils.generateNumbers(i);
            for (int j = maxThreads; j > 0; j--) {
                Result result = runByThreads(numbers, j);
                results.add(result);
                System.out.println("Threads: " + j + ", Numbers: " + i + ", Time: " + result.getTime());
            }
        }
        return results;
    }

    private static Result runByThreads(long[] numbers, int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException("Threads count must be greater than 0");
        }
        Min minValue = new Min();
        Max maxValue = new Max();
        Result result = new Result(numbers.length, threads);
        if (threads == 1) {
            long measuredTime = measureTime(() -> count(numbers, 0, numbers.length, minValue, maxValue));
            result.setTime(measuredTime);
            return result;
        }
        long measuredTime = measureTime(() -> {
            try {
                ExecutorService executor = Executors.newFixedThreadPool(threads);
                int size = numbers.length / threads + 1;
                for (int i = 0; i < threads; i++) {
                    int finalI = i;
                    executor.submit(() -> count(
                            numbers, finalI * size, min(numbers.length, (finalI + 1) * size),
                            minValue, maxValue
                    ));
                }
                executor.shutdown();
                boolean termination = executor.awaitTermination(2L, TimeUnit.MINUTES);
                if (!termination) {
                    throw new RuntimeException("Threads are not terminated");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        result.setTime(measuredTime);
        getRequest(BASE_URL + result.getSaveRequest());
        return result;
    }

    private static void count(long[] numbers, int startIndex, int endIndex, Min minValue, Max maxValue) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = startIndex; i < endIndex; i++) {
            final long number = numbers[i] * 2815462330234958L / 236508923645L - numbers[i] + 257438597203L
                    % 4832748932174L * numbers[i] / 4326054227567L + 756427562954532L - 2859346573465782346L;
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }
        minValue.setValue(min);
        maxValue.setValue(max);
    }
}
