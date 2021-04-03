package dev.evertonsavio.app;

import dev.evertonsavio.app.services.BankService;
import dev.evertonsavio.app.services.TransferService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 6565;

        Server server = ServerBuilder.forPort(port)
                .addService(new BankService())
                .addService(new TransferService())
                .build();

        server.start();
        System.out.println("Server started on port: " + port);
        server.awaitTermination();

    }
}
