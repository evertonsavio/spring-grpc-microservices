package dev.evertonsavio.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: movies.proto")
public final class MovieServiceGrpc {

  private MovieServiceGrpc() {}

  public static final String SERVICE_NAME = "MovieService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.evertonsavio.proto.MovieSearchRequest,
      dev.evertonsavio.proto.MovieSearchResponse> getGetMoviesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMovies",
      requestType = dev.evertonsavio.proto.MovieSearchRequest.class,
      responseType = dev.evertonsavio.proto.MovieSearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.evertonsavio.proto.MovieSearchRequest,
      dev.evertonsavio.proto.MovieSearchResponse> getGetMoviesMethod() {
    io.grpc.MethodDescriptor<dev.evertonsavio.proto.MovieSearchRequest, dev.evertonsavio.proto.MovieSearchResponse> getGetMoviesMethod;
    if ((getGetMoviesMethod = MovieServiceGrpc.getGetMoviesMethod) == null) {
      synchronized (MovieServiceGrpc.class) {
        if ((getGetMoviesMethod = MovieServiceGrpc.getGetMoviesMethod) == null) {
          MovieServiceGrpc.getGetMoviesMethod = getGetMoviesMethod = 
              io.grpc.MethodDescriptor.<dev.evertonsavio.proto.MovieSearchRequest, dev.evertonsavio.proto.MovieSearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "MovieService", "getMovies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.evertonsavio.proto.MovieSearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.evertonsavio.proto.MovieSearchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MovieServiceMethodDescriptorSupplier("getMovies"))
                  .build();
          }
        }
     }
     return getGetMoviesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MovieServiceStub newStub(io.grpc.Channel channel) {
    return new MovieServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MovieServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MovieServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MovieServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MovieServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MovieServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getMovies(dev.evertonsavio.proto.MovieSearchRequest request,
        io.grpc.stub.StreamObserver<dev.evertonsavio.proto.MovieSearchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMoviesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetMoviesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                dev.evertonsavio.proto.MovieSearchRequest,
                dev.evertonsavio.proto.MovieSearchResponse>(
                  this, METHODID_GET_MOVIES)))
          .build();
    }
  }

  /**
   */
  public static final class MovieServiceStub extends io.grpc.stub.AbstractStub<MovieServiceStub> {
    private MovieServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMovies(dev.evertonsavio.proto.MovieSearchRequest request,
        io.grpc.stub.StreamObserver<dev.evertonsavio.proto.MovieSearchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMoviesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MovieServiceBlockingStub extends io.grpc.stub.AbstractStub<MovieServiceBlockingStub> {
    private MovieServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.evertonsavio.proto.MovieSearchResponse getMovies(dev.evertonsavio.proto.MovieSearchRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMoviesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MovieServiceFutureStub extends io.grpc.stub.AbstractStub<MovieServiceFutureStub> {
    private MovieServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MovieServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MovieServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MovieServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.evertonsavio.proto.MovieSearchResponse> getMovies(
        dev.evertonsavio.proto.MovieSearchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMoviesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_MOVIES = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MovieServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MovieServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_MOVIES:
          serviceImpl.getMovies((dev.evertonsavio.proto.MovieSearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.evertonsavio.proto.MovieSearchResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MovieServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MovieServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.evertonsavio.proto.Movies.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MovieService");
    }
  }

  private static final class MovieServiceFileDescriptorSupplier
      extends MovieServiceBaseDescriptorSupplier {
    MovieServiceFileDescriptorSupplier() {}
  }

  private static final class MovieServiceMethodDescriptorSupplier
      extends MovieServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MovieServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MovieServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MovieServiceFileDescriptorSupplier())
              .addMethod(getGetMoviesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
