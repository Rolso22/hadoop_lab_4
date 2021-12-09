package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
import ru.bmstu.hadoop.labs.Contracts.PostRequest;

public class RouteActor extends AbstractActor {

    private static ActorRef storeActor;
    private static ActorRef executeActor;

    public RouteActor() {
        storeActor = context().actorOf(Props.create(StoreActor.class));
        executeActor = context().actorOf(Props.create(ExecuteTestActor.class));
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetRequest.class, msg -> storeActor.tell(msg, sender()))
                .match(PostRequest.class, msg -> executeActor.tell(msg, ActorRef.noSender()))
                .build();
    }
}
