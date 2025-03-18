package dev.kovaliv.data;

import dev.kovaliv.utils.CpuParams;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int counts;

    private int threads;
    private long time;

    private double frequency;
    private int physicalCores;
    private int logicalCores;

    public Result(int counts, int threads) {
        this.counts = counts;
        this.threads = threads;
        frequency = CpuParams.getFrequency();
        physicalCores = CpuParams.getPhysicalCores();
        logicalCores = CpuParams.getLogicalCores();
    }

    public static String getCsvHeader() {
        return "Counts,Threads,Time (ms),Frequency,Physical Cores,Logical Cores\n";
    }

    public String getCsvRow() {
        return counts + "," + threads + "," + time + "," + frequency + "," + physicalCores + "," + logicalCores + "\n";
    }

    public String getSaveRequest() {
        return "/treads?counts=" + counts + "&threads=" + threads + "&time=" + time
                + "&frequency=" + frequency + "&physical_cores=" + physicalCores + "&logical_cores=" + logicalCores;
    }
}
