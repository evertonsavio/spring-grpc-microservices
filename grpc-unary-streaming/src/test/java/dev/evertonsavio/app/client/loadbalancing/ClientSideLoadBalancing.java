package dev.evertonsavio.app.client.loadbalancing;

import dev.evertonsavio.app.client.rpctypes.BalanceStreamObserver;
import dev.evertonsavio.app.models.Balance;
import dev.evertonsavio.app.models.BalanceCheckRequest;
import dev.evertonsavio.app.models.BankServiceGrpc;
import dev.evertonsavio.app.models.DepositRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverRegistry;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //JUnit5 avoid needed for static method below
public class ClientSideLoadBalancing {

    private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    private BankServiceGrpc.BankServiceStub bankServiceStub;

    @BeforeAll
    public void setup(){

        ServiceRegistry.register("bank-service", List.of("localhost:6565", "localhost:7575"));
        NameResolverRegistry.getDefaultRegistry().register(new TempNameResolverProvider());

        ManagedChannel managedChannel = ManagedChannelBuilder
                //.forAddress("localhost", 8585) //nginx port
                .forTarget("bank-service")
                .defaultLoadBalancingPolicy("round_robin")
                .usePlaintext()
                .build();

        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void balanceTest(){

        for (int i = 1; i < 11; i++) {

            BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                    .setAccountNumber(i)
                    .build();

            Balance balance = this.blockingStub.getBalance(balanceCheckRequest);
            System.out.println("Received: " + balance.getAmount());
        }
    }


    @Test
    public void cashStreamingRequest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<DepositRequest> streamObserver = this.bankServiceStub
                .cashDeposit(new BalanceStreamObserver(latch));
        for (int i = 0; i < 10; i++) {
            DepositRequest depositRequest = DepositRequest.newBuilder().setAccountNumber(8).setAmount(10).build();
            streamObserver.onNext(depositRequest);
        }
        streamObserver.onCompleted();
        latch.await();
    }

}
