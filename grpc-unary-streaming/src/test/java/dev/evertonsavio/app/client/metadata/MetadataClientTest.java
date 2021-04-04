package dev.evertonsavio.app.client.metadata;

import dev.evertonsavio.app.client.deadline.DeadlineInterceptor;
import dev.evertonsavio.app.client.rpctypes.MoneyStramingResponse;
import dev.evertonsavio.app.models.Balance;
import dev.evertonsavio.app.models.BalanceCheckRequest;
import dev.evertonsavio.app.models.BankServiceGrpc;
import dev.evertonsavio.app.models.WithdrawRequest;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //JUnit5 avoid needed for static method below
public class MetadataClientTest {

    private BankServiceGrpc.BankServiceBlockingStub blockingStub;
    private BankServiceGrpc.BankServiceStub bankServiceStub;

    @BeforeAll
    public void setup(){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .intercept(MetadataUtils.newAttachHeadersInterceptor(ClientConstants.getClientToken()))
                .usePlaintext()
                .build();

        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void balanceTest(){

        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(7)
                .build();
        for (int i = 0; i < 20; i++) {

            try {
                int random = ThreadLocalRandom.current().nextInt(1, 4);
                System.out.println("RANDOM: "+ random);
                Balance balance = this.blockingStub
                        //.withDeadline(Deadline.after(2, TimeUnit.SECONDS))
                        .withCallCredentials(new UserSessionToken("user-secret-" + random))
                        .getBalance(balanceCheckRequest);
                System.out.println("Received: " + balance.getAmount());
            }
            catch (StatusRuntimeException e){
                e.printStackTrace();
                //go with deafault value
            }
        }

    }

    @Test
    public void withdrawTest() {
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(7)
                .setAmount(40).build();
        try {
            this.blockingStub
                    .withDeadline(Deadline.after(2, TimeUnit.SECONDS))
                    .withdraw(withdrawRequest)
                    .forEachRemaining(money -> System.out.println("Received: " + money.getValue()));
        } catch (StatusRuntimeException e) {
            System.out.println(e);
            //default...
        }
    }
    @Test
    public void withdrawAsyncTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder().setAccountNumber(10).setAmount(50).build();
        this.bankServiceStub.withdraw(withdrawRequest, new MoneyStramingResponse(latch));
        latch.await();
        //Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }

}
