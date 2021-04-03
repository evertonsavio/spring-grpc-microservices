package dev.evertonsavio.app.services;

import dev.evertonsavio.app.models.TransferRequest;
import dev.evertonsavio.app.models.TransferResponse;
import dev.evertonsavio.app.models.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferStreamingRequest(responseObserver);
    }
}
