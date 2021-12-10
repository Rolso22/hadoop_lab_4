package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
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
        System.out.println(store.entrySet());
        if (!store.containsKey(msg.getPackageId())) {
            store.put(msg.getPackageId(), new HashMap<>());
            System.out.println("put: " + msg.getPackageId());
        }
        System.out.println(msg.getPackageId() + " " + msg.getName() + " " + msg.getResult());
        store.get(msg.getPackageId()).put(msg.getName(), msg.getResult());
    }

    private void getPackage(GetRequest msg) {
        System.out.println(store.get(msg.getPackageId()).entrySet());
        sender().tell(store.get(msg.getPackageId()).entrySet().toString(), ActorRef.noSender());
    }

}
