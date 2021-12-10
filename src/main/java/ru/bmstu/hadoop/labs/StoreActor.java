package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
import ru.bmstu.hadoop.labs.Contracts.Result;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(store.entrySet());
        if (!store.containsKey(msg.getPackageId())) {
            store.put(msg.getPackageId(), new HashMap<>());
        }
        store.get(msg.getPackageId()).put(msg.getName(), msg.getResult());
    }

    private void getPackage(GetRequest msg) {
        StringBuilder packageTest = new StringBuilder();
        store.get(msg.getPackageId()).forEach((key, value) -> packageTest
                .append("Name: ").append(key).append("\n")
                .append("Result: ").append(value).append("\n"));
        sender().tell(String.valueOf(packageTest), ActorRef.noSender());
    }

}
