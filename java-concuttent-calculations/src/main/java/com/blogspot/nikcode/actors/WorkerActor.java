package com.blogspot.nikcode.actors;

import akka.actor.UntypedActor;
import java.util.List;

/**
 *
 * @author nik
 */
public class WorkerActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        List<Integer> list = (List<Integer>) msg;
        long result = 0L;
        for (int elem : list) {
            result += elem;
        }
        getSender().tell(result);
    }
}
