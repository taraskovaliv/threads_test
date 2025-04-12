package dev.kovaliv;

import dev.kovaliv.data.Result;
import dev.kovaliv.tasks.MinMax;
import dev.kovaliv.utils.ResultUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                List<Result> results = new MinMax().run(100);
                ResultUtils.saveResult(results);
            } catch (Error e) {
                System.gc();
            }
        }
    }
}