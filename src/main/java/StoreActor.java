import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.Locale;

public class StoreActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, System.out::println)
                .build();
    }
}
