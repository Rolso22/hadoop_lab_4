package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.Test;

public class ExecuteTestActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Test.class, msg -> {

                })
                .build();
    }

}
