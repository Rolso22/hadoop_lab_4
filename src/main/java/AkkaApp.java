import akka.actor.ActorRef;
import akka.actor.Props;

public class AkkaApp {
    public static void main(String[] args) {
        Props props = Props.create(ResultActor.class);
        ActorRef storeActor = system.actorOf(props);
    }
}
