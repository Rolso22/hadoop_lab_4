import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ExecuteTestActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, System.out::println)
                .build();
    }
}
