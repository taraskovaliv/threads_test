package dev.kovaliv.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.util.Arrays;
import java.util.OptionalDouble;

public class CpuParams {
    public static int getPhysicalCores() {
        CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
        return processor.getPhysicalProcessorCount();
    }

    public static int getLogicalCores() {
        CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
        return processor.getLogicalProcessorCount();
    }

    public static double getFrequency() {
        CentralProcessor processor = new SystemInfo().getHardware().getProcessor();
        OptionalDouble average = Arrays.stream(processor.getCurrentFreq())
                .asDoubleStream()
                .map(f -> f / 1_000_000.0)
                .average();
        return average.orElse(0.0);
    }
}
