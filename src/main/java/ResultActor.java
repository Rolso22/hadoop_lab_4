import akka.actor.AbstractActor;

import java.util.Locale;

public class ResultActor extends AbstractActor {

    private String str;

    @Override
    public Receive createReceive() {
        System.out.println("HERE");
        return receiveBuilder()
                .match(String.class, s -> getSender().tell(s.toUpperCase(Locale.ROOT), self()))
                .build();
    }

    public Object StoreMessage(String test) {
        return test.toUpperCase(Locale.ROOT);
    }
}
