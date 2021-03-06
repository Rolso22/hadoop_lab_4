package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import ru.bmstu.hadoop.labs.Contracts.GetRequest;
import ru.bmstu.hadoop.labs.Contracts.TestPackage;
import ru.bmstu.hadoop.labs.Contracts.Test;

import java.util.ArrayList;
import java.util.List;
import static ru.bmstu.hadoop.labs.Constants.*;

public class RouteActor extends AbstractActor {

    private final ActorRef storeActor;
    private Router router;

    {
        storeActor = getContext().actorOf(Props.create(StoreActor.class));

        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_EXECUTERS; i++) {
            ActorRef executeActor = getContext().actorOf(Props.create(ExecuteTestActor.class));
            getContext().watch(executeActor);
            routees.add(new ActorRefRoutee(executeActor));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetRequest.class, msg -> storeActor.tell(msg, sender()))
                .match(TestPackage.class, this::executeTests)
                .match(Terminated.class, this::restartTerminatedExecutor)
                .build();
    }

    private void executeTests(TestPackage msg) {
        for (Test test : msg.getTests()) {
            test.setFnName(msg.getFnName());
            test.setJsScript(msg.getJsScript());
            test.setPackageId(msg.getPackageId());
            router.route(test, storeActor);
        }
    }

    private void restartTerminatedExecutor(Terminated msg) {
        router = router.removeRoutee(msg.actor());
        ActorRef executeActor = getContext().actorOf(Props.create(ExecuteTestActor.class));
        getContext().watch(executeActor);
        router = router.addRoutee(new ActorRefRoutee(executeActor));
    }
}
