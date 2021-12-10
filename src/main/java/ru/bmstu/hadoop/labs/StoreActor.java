package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.Result;

import java.util.HashMap;

public class StoreActor extends AbstractActor {
    private final HashMap<String, HashMap<String, String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Result.class, this::saveResult)
                .build();
    }

    private void saveResult(Result msg) {
        if (!store.containsKey(msg.getPackageId())) {
            store.put(msg.getPackageId(), new HashMap<>());
        }

    }
}
