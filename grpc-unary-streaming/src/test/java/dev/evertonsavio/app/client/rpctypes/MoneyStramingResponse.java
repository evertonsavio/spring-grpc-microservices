package dev.evertonsavio.app.client.rpctypes;

import dev.evertonsavio.app.models.Money;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class MoneyStramingResponse implements StreamObserver<Money>{

    private CountDownLatch latch;

    public MoneyStramingResponse(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(Money money) {
        System.out.println("Received async" + money.getValue());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Server done");
        latch.countDown();
    }
}
