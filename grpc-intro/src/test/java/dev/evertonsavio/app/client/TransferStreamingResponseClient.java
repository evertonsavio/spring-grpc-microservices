package dev.evertonsavio.app.client;

import dev.evertonsavio.app.models.TransferResponse;
import io.grpc.stub.StreamObserver;

import java.sql.SQLOutput;
import java.util.concurrent.CountDownLatch;

public class TransferStreamingResponseClient implements StreamObserver<TransferResponse> {

    private CountDownLatch latch;

    public TransferStreamingResponseClient(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(TransferResponse transferResponse) {
        System.out.println("Status" + transferResponse.getStatus());
        transferResponse.getAccountsList()
                .stream()
                .map(account -> account.getAccountNumber() + " : " + account.getAmount())
                .forEach(System.out::println);
    }

    @Override
    public void onError(Throwable throwable) {
        this.latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Completed");
        this.latch.countDown();
    }
}
