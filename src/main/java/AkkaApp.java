import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef storeActor = system.actorOf(Props.create(ResultActor.class, () -> new ResultActor("test")));
        
        storeActor.tell(Props.create(ResultActor.class), ActorRef.noSender());

    }
}
