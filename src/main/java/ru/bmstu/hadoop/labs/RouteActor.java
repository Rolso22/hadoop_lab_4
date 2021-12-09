package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;

public class RouteActor extends AbstractActor {

    public RouteActor() {
        
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetRequest.class, msg -> System.out.println(msg.getPackageId()))
                .build();
    }
}
