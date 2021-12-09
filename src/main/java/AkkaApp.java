import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("test");
        ActorRef actor = system.actorOf(Props.create(ResultActor.class));
        actor.tell("test", ActorRef.noSender());

    }
}
