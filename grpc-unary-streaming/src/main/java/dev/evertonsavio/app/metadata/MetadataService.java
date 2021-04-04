package dev.evertonsavio.app.metadata;

import com.google.common.util.concurrent.Uninterruptibles;
import dev.evertonsavio.app.models.*;
import dev.evertonsavio.app.rpctypes.data.AccountDatabase;
import dev.evertonsavio.app.rpctypes.services.CashStreamingRequest;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.protobuf.ProtoUtils;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class MetadataService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {

        int accountNumber = request.getAccountNumber();

        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(accountNumber))
                .build();

        //simulate time consuming request
        //Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver){

        int accountNumber = request.getAccountNumber();
        int amount = request.getAmount(); //10, 20, 30..
        int balance = AccountDatabase.getBalance(accountNumber);

        if(amount < 10 || (amount % 10) != 0){
            Metadata metadata = new Metadata();
            Metadata.Key<WithdrawError> withdrawErrorKey = ProtoUtils.keyForProto(WithdrawError.getDefaultInstance());
            WithdrawError withdrawError = WithdrawError.newBuilder().setAmount(balance).setErrorMessage(ErrorMessage.ONLY_TEN_MULTIPLUS).build();
            metadata.put(withdrawErrorKey, withdrawError);

            //Status status = Status.FAILED_PRECONDITION.withDescription("No enough money: " + balance);
            responseObserver.onError(Status.FAILED_PRECONDITION.asRuntimeException(metadata));
            return;
        }

        if(balance < amount){

            //ERROR PROTO HANDLER
            Metadata metadata = new Metadata();
            Metadata.Key<WithdrawError> withdrawErrorKey = ProtoUtils.keyForProto(WithdrawError.getDefaultInstance());
            WithdrawError withdrawError = WithdrawError.newBuilder().setAmount(balance).setErrorMessage(ErrorMessage.INSUFFICIENT_BALANCE).build();
            metadata.put(withdrawErrorKey, withdrawError);

            //Status status = Status.FAILED_PRECONDITION.withDescription("No enough money: " + balance);
            responseObserver.onError(Status.FAILED_PRECONDITION.asRuntimeException(metadata));
            return;
        }

        //JUST TO CREATE STREAM REQUEST
        for (int i = 0; i < amount/10; i++) {
            Money money = Money.newBuilder().setValue(10).build();

            //simulate time consuming request
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

            //check if client still connected because of timeout issues
            if(!Context.current().isCancelled()){
                //System.out.println("Delivered 10$");
                responseObserver.onNext(money);
                AccountDatabase.deductBalance(accountNumber, 10);
            }else
                break;
        }
        //System.out.println("Completed");
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DepositRequest> cashDeposit(StreamObserver<Balance> responseObserver) {
        return new CashStreamingRequest(responseObserver);
    }
}
