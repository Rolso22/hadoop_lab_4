package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;

public class RouteActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetRequest.class, s -> )
                .build();
    }
}
