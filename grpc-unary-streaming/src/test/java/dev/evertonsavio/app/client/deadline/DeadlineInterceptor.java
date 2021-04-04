package dev.evertonsavio.app.client.deadline;

import io.grpc.*;

import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeadlineInterceptor implements ClientInterceptor {


    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {

        //return channel.newCall(methodDescriptor, callOptions); //not doing anything

        Deadline deadline = callOptions.getDeadline();
        if(Objects.isNull(deadline)){
            callOptions = callOptions.withDeadline(Deadline.after(4, TimeUnit.SECONDS));
        }
        return channel.newCall(methodDescriptor, callOptions);
        //return channel.newCall(methodDescriptor, callOptions.withDeadline(Deadline.after(4, TimeUnit.SECONDS)));
    }
}
