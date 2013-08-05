package com.blogspot.nikcode.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author nik
 */
public class Task extends RecursiveTask<Long> {

    private final List<Integer> list;

    public Task(List<Integer> list) {
        this.list = list;
    }
    
    @Override
    protected Long compute() {
        long result = 0L;
        for (int elem : list) {
            result += elem;
        }
        return result;
    }
}
