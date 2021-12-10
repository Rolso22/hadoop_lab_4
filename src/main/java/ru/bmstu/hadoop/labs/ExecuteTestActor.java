package ru.bmstu.hadoop.labs;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.hadoop.labs.Contracts.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

    private void execute(Test msg) throws ScriptException, NoSuchMethodException {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(msg.getJsScript());
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(msg.getFnName(), Arrays.msg.getParams()).toString();
        //storeActor.tell(, ActorRef.noSender());
        System.out.println(result);
    }

}
