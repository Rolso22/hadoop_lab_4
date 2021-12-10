package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
import ru.bmstu.hadoop.labs.Contracts.Response;
import ru.bmstu.hadoop.labs.Contracts.Result;
import java.util.HashMap;

public class StoreActor extends AbstractActor {
    private final HashMap<String, HashMap<String, String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, this::saveResult)
                .match(GetRequest.class, this::getPackage)
                .build();
    }

    private void saveResult(Result msg) {
        if (!store.containsKey(msg.getPackageId())) {
            store.put(msg.getPackageId(), new HashMap<>());
        }
        store.get(msg.getPackageId()).put(msg.getName(), msg.getResult());
    }

    private void getPackage(GetRequest msg) {
        sender().tell(new Response(msg.getPackageId(), store.get(msg.getPackageId())), ActorRef.noSender());
    }

}
