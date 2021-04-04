package dev.evertonsavio.app.loadbalancing;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer01 {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 6565;

        Server server = ServerBuilder.forPort(port)
                .addService(new BankService())
                .build();

        server.start();
        System.out.println("Server started on port: " + port);
        server.awaitTermination();

    }
}
