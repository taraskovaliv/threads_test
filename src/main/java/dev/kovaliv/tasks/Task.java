package dev.kovaliv.tasks;

import dev.kovaliv.data.Result;

import java.util.List;
import java.util.Map;

public interface Task {
    String getName();

    List<Result> run(int maxThreads);
}
