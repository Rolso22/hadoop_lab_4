package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
import ru.bmstu.hadoop.labs.Contracts.PostRequest;
import ru.bmstu.hadoop.labs.Contracts.Test;

import java.util.ArrayList;
import java.util.List;
import static ru.bmstu.hadoop.labs.Constants.*;

public class RouteActor extends AbstractActor {

    private final ActorRef storeActor;
    private Router router;

    public RouteActor() {
        storeActor = context().actorOf(Props.create(StoreActor.class));

        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_EXECUTERS; i++) {
            ActorRef r = getContext().actorOf(Props.create(ExecuteTestActor.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetRequest.class, msg -> storeActor.tell(msg, sender()))
                .match(PostRequest.class, this::executeTests)
                .match(Terminated.class, msg -> {
                    router = router.removeRoutee(msg.actor());
                    ActorRef r = getContext().actorOf(Props.create(ExecuteTestActor.class));
                    getContext().watch(r);
                    router = router.addRoutee(new ActorRefRoutee(r));
                })
                .build();
    }

    private void executeTests(PostRequest msg) {
        for (Test test : msg.getTests()) {
            router.route(test, sender());
        }
    }

}
