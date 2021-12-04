import akka.actor.AbstractActor;

import java.util.Locale;

public class ResultActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> getSender()
    }

    public Object StoreMessage(String test) {
        return test.toUpperCase(Locale.ROOT);
    }
}
