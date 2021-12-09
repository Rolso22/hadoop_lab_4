import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import java.util.Locale;

public class ResultActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(String.class, System.out::println)
                .build();
    }
}
