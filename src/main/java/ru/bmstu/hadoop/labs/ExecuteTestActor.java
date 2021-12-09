package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.PostRequest;

public class ExecuteTestActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(PostRequest.class, msg -> System.out.println(msg.getFnName() + " " + msg.getTests()))
                .build();
    }
}
