package dev.kovaliv;

import dev.kovaliv.data.Result;
import dev.kovaliv.tasks.MinMax;
import dev.kovaliv.utils.ResultUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Result> results = new MinMax().run(100);
        ResultUtils.saveResult(results);
    }
}