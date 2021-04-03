package dev.evertonsavio.app.client;

import dev.evertonsavio.app.models.BankServiceGrpc;
import dev.evertonsavio.app.models.TransferRequest;
import dev.evertonsavio.app.models.TransferServiceGrpc;
import dev.evertonsavio.app.services.TransferStreamingRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransferClientTest {


    private TransferServiceGrpc.TransferServiceStub stub;

    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        this.stub = TransferServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void transfer() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        TransferStreamingResponseClient responseClient = new TransferStreamingResponseClient(latch);
        StreamObserver<TransferRequest> requestStreamObserver = this.stub.transfer(responseClient);

        for (int i = 0; i < 100; i++) {
            TransferRequest transferRequest = TransferRequest.newBuilder()
                    .setFromAccount(ThreadLocalRandom.current().nextInt(1, 11))
                    .setToAccount(ThreadLocalRandom.current().nextInt(1, 11))
                    .setAmount(ThreadLocalRandom.current().nextInt(1, 21))
                    .build();

            requestStreamObserver.onNext(transferRequest);
        }
        requestStreamObserver.onCompleted();
        latch.await();
    }

}
