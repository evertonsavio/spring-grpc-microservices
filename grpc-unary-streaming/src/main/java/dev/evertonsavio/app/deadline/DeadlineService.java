package dev.evertonsavio.app.deadline;

import com.google.common.util.concurrent.Uninterruptibles;
import dev.evertonsavio.app.models.*;
import dev.evertonsavio.app.rpctypes.data.AccountDatabase;
import dev.evertonsavio.app.rpctypes.services.CashStreamingRequest;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class DeadlineService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {

        int accountNumber = request.getAccountNumber();

        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(accountNumber))
                .build();

        //simulate time consuming request
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver){

        int accountNumber = request.getAccountNumber();
        int amount = request.getAmount(); //10, 20, 30..
        int balance = AccountDatabase.getBalance(accountNumber);

        if(balance < amount){
            Status status = Status.FAILED_PRECONDITION.withDescription("No enough money: " + balance);
            responseObserver.onError(status.asRuntimeException());
            return;
        }

        //JUST TO CREATE STREAM REQUEST
        for (int i = 0; i < amount/10; i++) {
            Money money = Money.newBuilder().setValue(10).build();

            //simulate time consuming request
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

            //check if client still connected because of timeout issues
            if(!Context.current().isCancelled()){
                System.out.println("Delivered 10$");
                responseObserver.onNext(money);
                AccountDatabase.deductBalance(accountNumber, 10);
            }else
                break;
        }
        System.out.println("Completed");
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return new CashStreamingRequest(responseObserver);
    }
}
