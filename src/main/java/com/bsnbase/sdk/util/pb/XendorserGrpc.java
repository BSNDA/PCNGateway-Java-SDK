package com.baidu.xuper.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.40.1)",
    comments = "Source: xendorser.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class XendorserGrpc {

  private XendorserGrpc() {}

  public static final String SERVICE_NAME = "pb.xendorser";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest,
      com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> getEndorserCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EndorserCall",
      requestType = com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest.class,
      responseType = com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest,
      com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> getEndorserCallMethod() {
    io.grpc.MethodDescriptor<com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest, com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> getEndorserCallMethod;
    if ((getEndorserCallMethod = XendorserGrpc.getEndorserCallMethod) == null) {
      synchronized (XendorserGrpc.class) {
        if ((getEndorserCallMethod = XendorserGrpc.getEndorserCallMethod) == null) {
          XendorserGrpc.getEndorserCallMethod = getEndorserCallMethod =
              io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest, com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EndorserCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new XendorserMethodDescriptorSupplier("EndorserCall"))
              .build();
        }
      }
    }
    return getEndorserCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static XendorserStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<XendorserStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<XendorserStub>() {
        @java.lang.Override
        public XendorserStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new XendorserStub(channel, callOptions);
        }
      };
    return XendorserStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static XendorserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<XendorserBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<XendorserBlockingStub>() {
        @java.lang.Override
        public XendorserBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new XendorserBlockingStub(channel, callOptions);
        }
      };
    return XendorserBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static XendorserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<XendorserFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<XendorserFutureStub>() {
        @java.lang.Override
        public XendorserFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new XendorserFutureStub(channel, callOptions);
        }
      };
    return XendorserFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class XendorserImplBase implements io.grpc.BindableService {

    /**
     */
    public void endorserCall(com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEndorserCallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEndorserCallMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest,
                com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse>(
                  this, METHODID_ENDORSER_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class XendorserStub extends io.grpc.stub.AbstractAsyncStub<XendorserStub> {
    private XendorserStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XendorserStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new XendorserStub(channel, callOptions);
    }

    /**
     */
    public void endorserCall(com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest request,
        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEndorserCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class XendorserBlockingStub extends io.grpc.stub.AbstractBlockingStub<XendorserBlockingStub> {
    private XendorserBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XendorserBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new XendorserBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse endorserCall(com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEndorserCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class XendorserFutureStub extends io.grpc.stub.AbstractFutureStub<XendorserFutureStub> {
    private XendorserFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected XendorserFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new XendorserFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse> endorserCall(
        com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEndorserCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ENDORSER_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final XendorserImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(XendorserImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ENDORSER_CALL:
          serviceImpl.endorserCall((com.baidu.xuper.pb.XendorserOuterClass.EndorserRequest) request,
              (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XendorserOuterClass.EndorserResponse>) responseObserver);
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

  private static abstract class XendorserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    XendorserBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.baidu.xuper.pb.XendorserOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Xendorser");
    }
  }

  private static final class XendorserFileDescriptorSupplier
      extends XendorserBaseDescriptorSupplier {
    XendorserFileDescriptorSupplier() {}
  }

  private static final class XendorserMethodDescriptorSupplier
      extends XendorserBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    XendorserMethodDescriptorSupplier(String methodName) {
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
      synchronized (XendorserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new XendorserFileDescriptorSupplier())
              .addMethod(getEndorserCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
