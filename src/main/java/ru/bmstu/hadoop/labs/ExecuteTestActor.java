package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.Test;

public class ExecuteTestActor extends AbstractActor {

    private final ActorRef storeActor;

    {
        storeActor = context().actorOf(Props.create(StoreActor.class));
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Test.class, this::execute)
                .build();
    }

    private void execute(Test msg) {
        
    }

}
