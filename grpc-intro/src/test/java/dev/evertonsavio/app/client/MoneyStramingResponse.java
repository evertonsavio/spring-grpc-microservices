package dev.evertonsavio.app.client;

import dev.evertonsavio.app.models.Money;
import io.grpc.stub.StreamObserver;

public class MoneyStramingResponse implements StreamObserver<Money>{

    @Override
    public void onNext(Money money) {
        System.out.println("Received async" + money.getValue());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Server done");
    }
}
