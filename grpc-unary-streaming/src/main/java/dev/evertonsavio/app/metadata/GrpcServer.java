package dev.evertonsavio.app.metadata;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 6565;

        Server server = ServerBuilder.forPort(port)
                //.intercept(new AuthInterceptor())
                .addService(new MetadataService())
                .build();

        server.start();
        System.out.println("Server started on port: " + port);
        server.awaitTermination();

    }
}
