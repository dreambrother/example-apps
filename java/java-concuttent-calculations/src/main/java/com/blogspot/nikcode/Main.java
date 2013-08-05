package com.blogspot.nikcode;

import akka.actor.*;
import com.blogspot.nikcode.actors.DispatcherActor;
import com.blogspot.nikcode.executors.Job;
import com.blogspot.nikcode.forkjoin.DispatcherAction;
import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author nik
 */
public class Main {
    
    private static final ResultPrinter resultPrinter = new ResultPrinter();
    private static final int TEST_EXECUTION_COUNT = 10;
    
    public static void main(String[] args) {
        final int listSize = 10000000;
        List<Integer> list = new ArrayList<>(listSize);
        Random random = new Random();
        
        // init array
        for (int i = 0; i < listSize; i++) {
            list.add(random.nextInt());
        }
        
        oneThreadCalc(list);
        multithreadCalc(list);
        forkJoinCalc(list);
        actorsCalc(list);
    }
    
    private static void oneThreadCalc(List<Integer> list) {
        System.out.println("------ oneThreadCalc -------");
        
        for (int i = 0; i < TEST_EXECUTION_COUNT; i++) {
            resultPrinter.submitStart();
            long result = 0L;
            for (int elem : list) {
                result += elem;
            }
            resultPrinter.submitFinish(result);
        }
    }
    
    private static void actorsCalc(List<Integer> list) {
        System.out.println("------ actorsCalc -------");
        for (int i = 0; i < TEST_EXECUTION_COUNT; i++) {
            final int workersCount = 4;

            ActorSystem system = ActorSystem.create();
            ActorRef actor = system.actorOf(new Props(new UntypedActorFactory() {
                @Override
                public Actor create() {
                    return new DispatcherActor(workersCount);
                }
            }));
            actor.tell(list);
        }
    }
    
    private static void multithreadCalc(List<Integer> list) { 
        System.out.println("------ multithreadCalc -------");
        for (int i = 0; i < TEST_EXECUTION_COUNT; i++) {
            resultPrinter.submitStart();

            final int workersCount = 4;
            final int numberOfElemsToJob = list.size() / workersCount;

            Collection<Callable<Long>> jobs = new ArrayList<>(workersCount);
            for (int k = 0; k < workersCount; k++) {
                jobs.add(new Job(list.subList(k * numberOfElemsToJob, ((k * numberOfElemsToJob) + numberOfElemsToJob)))); 
            }

            ExecutorService executorService = Executors.newFixedThreadPool(workersCount);
            try {
                long result = 0L;
                List<Future<Long>> futures = executorService.invokeAll(jobs);
                for (Future<Long> future : futures) {
                    result += future.get();
                }
                resultPrinter.submitFinish(result);
                executorService.shutdown();
            } catch (InterruptedException | ExecutionException ex) {
                throw new IllegalStateException();
            }
        }
    }
    
    private static void forkJoinCalc(List<Integer> list) {
        System.out.println("------ forkJoinCalc -------");
        for (int i = 0; i < TEST_EXECUTION_COUNT; i++) {
            final int workersCount = 4;
            new ForkJoinPool(workersCount).invoke(new DispatcherAction(list, workersCount));            
        }
    }
}
