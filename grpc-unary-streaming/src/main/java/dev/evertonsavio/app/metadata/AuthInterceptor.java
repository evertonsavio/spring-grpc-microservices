package dev.evertonsavio.app.metadata;

import io.grpc.*;

import java.util.Objects;

public class AuthInterceptor implements ServerInterceptor {


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {

        String clientToken = metadata.get(ServerConstants.USER_TOKEN);

        if(this.validateToken(clientToken)){
            return serverCallHandler.startCall(serverCall, metadata);
        }else{
            Status status = Status.UNAUTHENTICATED.withDescription("Invalid token/expired");
            serverCall.close(status, metadata);
        }
        return new ServerCall.Listener<>(){

        };
    }

    private boolean validateToken(String token){
        return Objects.nonNull(token) && token.equals("user-secret-3");
    }
}
