package if4031;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class ChatServiceGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<if4031.gString,
      if4031.gString> METHOD_SET_NICKNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "setNickname",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()));
  public static final io.grpc.MethodDescriptor<if4031.gString,
      if4031.gInt> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.gString,
      if4031.gInt> METHOD_LEAVE_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "leaveChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.paramSend,
      if4031.gInt> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.paramSend.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.paramSendTo,
      if4031.gInt> METHOD_SEND_MESSAGE_TO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "sendMessageTo",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.paramSendTo.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.gString,
      if4031.gInt> METHOD_EXIT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.ChatService", "exit",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.gString,
      if4031.Message> METHOD_RECEIVE_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          "if4031.ChatService", "receiveMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.Message.parser()));

  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  public static interface ChatService {

    public void setNickname(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gString> responseObserver);

    public void joinChannel(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver);

    public void leaveChannel(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver);

    public void sendMessage(if4031.paramSend request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver);

    public void sendMessageTo(if4031.paramSendTo request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver);

    public void exit(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver);

    public void receiveMessage(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.Message> responseObserver);
  }

  public static interface ChatServiceBlockingClient {

    public if4031.gString setNickname(if4031.gString request);

    public if4031.gInt joinChannel(if4031.gString request);

    public if4031.gInt leaveChannel(if4031.gString request);

    public if4031.gInt sendMessage(if4031.paramSend request);

    public if4031.gInt sendMessageTo(if4031.paramSendTo request);

    public if4031.gInt exit(if4031.gString request);

    public java.util.Iterator<if4031.Message> receiveMessage(
        if4031.gString request);
  }

  public static interface ChatServiceFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<if4031.gString> setNickname(
        if4031.gString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> joinChannel(
        if4031.gString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> leaveChannel(
        if4031.gString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> sendMessage(
        if4031.paramSend request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> sendMessageTo(
        if4031.paramSendTo request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> exit(
        if4031.gString request);
  }

  public static class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub>
      implements ChatService {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    @java.lang.Override
    public void setNickname(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void joinChannel(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void leaveChannel(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessage(if4031.paramSend request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessageTo(if4031.paramSendTo request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void exit(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void receiveMessage(if4031.gString request,
        io.grpc.stub.StreamObserver<if4031.Message> responseObserver) {
      asyncServerStreamingCall(
          channel.newCall(METHOD_RECEIVE_MESSAGE, callOptions), request, responseObserver);
    }
  }

  public static class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub>
      implements ChatServiceBlockingClient {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public if4031.gString setNickname(if4031.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public if4031.gInt joinChannel(if4031.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.gInt leaveChannel(if4031.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.gInt sendMessage(if4031.paramSend request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public if4031.gInt sendMessageTo(if4031.paramSendTo request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request);
    }

    @java.lang.Override
    public if4031.gInt exit(if4031.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @java.lang.Override
    public java.util.Iterator<if4031.Message> receiveMessage(
        if4031.gString request) {
      return blockingServerStreamingCall(
          channel.newCall(METHOD_RECEIVE_MESSAGE, callOptions), request);
    }
  }

  public static class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub>
      implements ChatServiceFutureClient {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gString> setNickname(
        if4031.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> joinChannel(
        if4031.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> leaveChannel(
        if4031.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> sendMessage(
        if4031.paramSend request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> sendMessageTo(
        if4031.paramSendTo request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.gInt> exit(
        if4031.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final ChatService serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("if4031.ChatService")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SET_NICKNAME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.gString,
                if4031.gString>() {
              @java.lang.Override
              public void invoke(
                  if4031.gString request,
                  io.grpc.stub.StreamObserver<if4031.gString> responseObserver) {
                serviceImpl.setNickname(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.gString,
                if4031.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.gString request,
                  io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
                serviceImpl.joinChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.gString,
                if4031.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.gString request,
                  io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
                serviceImpl.leaveChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.paramSend,
                if4031.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.paramSend request,
                  io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE_TO,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.paramSendTo,
                if4031.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.paramSendTo request,
                  io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
                serviceImpl.sendMessageTo(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_EXIT,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.gString,
                if4031.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.gString request,
                  io.grpc.stub.StreamObserver<if4031.gInt> responseObserver) {
                serviceImpl.exit(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_RECEIVE_MESSAGE,
          asyncServerStreamingCall(
            new io.grpc.stub.ServerCalls.ServerStreamingMethod<
                if4031.gString,
                if4031.Message>() {
              @java.lang.Override
              public void invoke(
                  if4031.gString request,
                  io.grpc.stub.StreamObserver<if4031.Message> responseObserver) {
                serviceImpl.receiveMessage(request, responseObserver);
              }
            }))).build();
  }
}
