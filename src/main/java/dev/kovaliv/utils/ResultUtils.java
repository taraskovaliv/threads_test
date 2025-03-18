package dev.kovaliv.utils;

import dev.kovaliv.data.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ResultUtils {
    public static void saveResult(List<Result> results) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("result.csv"));
            bw.write(Result.getCsvHeader());
            for (Result result : results) {
                bw.write(result.getCsvRow());
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
