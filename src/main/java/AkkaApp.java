import akka.actor.Props;

public class AkkaApp {
    public static void main(String[] args) {
        Props props = Props.create(ResultActor.class);
        
    }
}
