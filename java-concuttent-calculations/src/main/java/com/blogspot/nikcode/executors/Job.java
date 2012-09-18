package com.blogspot.nikcode.executors;

import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author nik
 */
public class Job implements Callable<Long> {

    private final List<Integer> list;
    
    public Job(List<Integer> list) {
        this.list = list;
    }
    
    @Override
    public Long call() throws Exception {
        long result = 0L;
        for (int elem : list) {
            result += elem;
        }
        return result;
    }
}
