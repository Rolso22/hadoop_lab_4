import akka.actor.AbstractActor;

import java.util.Locale;

public class ResultActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return null;
    }

    public Object StoreMessage(String test) {
        return test.toUpperCase(Locale.ROOT);
    }
}
