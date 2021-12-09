package ru.bmstu.hadoop.labs;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;
import ru.bmstu.hadoop.labs.Contracts.*;

public class AkkaApp {

    public AkkaApp() {}

    private static ActorRef router;

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("JSTesting");
        router = system.actorOf(Props.create(RouteActor.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        AkkaApp instance = new AkkaApp();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080");
        System.in.read();
        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute() {
        return route(
                get(() -> parameter("packageId", (id) -> {
                    Future<Object> result = Patterns.ask(router, new GetRequest(id), 5000);
                    //return completeOKWithFuture(result, Jackson.marshaller());
                    return complete("OK");
                })),
                post(() -> {
                    return complete("Received something else");
                })
        );
    }
}
