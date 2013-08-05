package com.blogspot.nikcode.actors;

import com.blogspot.nikcode.ResultPrinter;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Await;
import akka.dispatch.Future;
import akka.pattern.Patterns;
import akka.routing.RoundRobinRouter;
import akka.util.Timeout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nik
 */
public class DispatcherActor extends UntypedActor {

    private static final Timeout TIMEOUT = new Timeout(1000L);
    private final int workersCount;

    public DispatcherActor(int workersCount) {
        this.workersCount = workersCount;
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        ResultPrinter resultPrinter = new ResultPrinter();
        resultPrinter.submitStart();
        List<Integer> list = (List<Integer>) msg;
        
        int numberOfElemsToJob = list.size() / workersCount;
        
        ActorRef worker = getContext().actorOf(new Props(WorkerActor.class).withRouter(new RoundRobinRouter(workersCount)));
        List<Future<Object>> futures = new ArrayList<>(workersCount);
        for (int i = 0; i < workersCount; i++) {
            futures.add(Patterns.ask(worker, 
                    list.subList(i * numberOfElemsToJob, ((i * numberOfElemsToJob) + numberOfElemsToJob)), 
                    TIMEOUT)); // not cool, but i want to avoid unnecessary array coping in test app
        }
        long result = 0L;
        for (Future<Object> future : futures) {
            result += (Long) Await.result(future, TIMEOUT.duration());
        }
        
        resultPrinter.submitFinish(result);
        
        getContext().system().shutdown();
    }
}
