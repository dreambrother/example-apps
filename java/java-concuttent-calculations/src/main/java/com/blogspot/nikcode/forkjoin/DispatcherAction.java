package com.blogspot.nikcode.forkjoin;

import com.blogspot.nikcode.ResultPrinter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author nik
 */
public class DispatcherAction extends RecursiveAction {

    private final List<Integer> list;
    private final int workersCount;

    public DispatcherAction(List<Integer> list, int workersCount) {
        this.list = list;
        this.workersCount = workersCount;
    }
    
    @Override
    protected void compute() {
        ResultPrinter resultPrinter = new ResultPrinter();
        resultPrinter.submitStart();
        
        final int numberOfElemsToJob = list.size() / workersCount;
        
        Collection<RecursiveTask<Long>> tasks = new ArrayList<>(workersCount);
        for (int i = 0; i < workersCount; i++) {
            final Task task = new Task(list.subList(i * numberOfElemsToJob, ((i * numberOfElemsToJob) + numberOfElemsToJob)));
            task.fork();
            tasks.add(task); 
        }
        long result = 0L;
        for (RecursiveTask<Long> task : tasks) {
            result += task.join();
        }
        
        resultPrinter.submitFinish(result);
    }
}
