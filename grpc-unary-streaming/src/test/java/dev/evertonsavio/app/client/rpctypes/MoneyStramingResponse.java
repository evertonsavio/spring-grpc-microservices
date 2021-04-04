package dev.evertonsavio.app.client.rpctypes;

import dev.evertonsavio.app.client.metadata.ClientConstants;
import dev.evertonsavio.app.models.Money;
import dev.evertonsavio.app.models.WithdrawError;
import io.grpc.Metadata;
import io.grpc.Status;
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

        Status status = Status.fromThrowable(throwable);
        Metadata metadata = Status.trailersFromThrowable(throwable);
        WithdrawError withdrawError = metadata.get(ClientConstants.WITHDRAW_ERROR_KEY);
        System.out.println(withdrawError);
        //System.out.println(throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Server done");
        latch.countDown();
    }
}
