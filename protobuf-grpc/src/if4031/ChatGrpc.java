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
public class ChatGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<if4031.generated.gString,
      if4031.generated.gString> METHOD_SET_NICKNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "setNickname",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gString.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.paramSend,
      if4031.generated.gInt> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.paramSend.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.paramSend,
      if4031.generated.gInt> METHOD_LEAVE_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "leaveChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.paramSend.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.paramSend,
      if4031.generated.gInt> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.paramSend.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.paramSendTo,
      if4031.generated.gInt> METHOD_SEND_MESSAGE_TO =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "sendMessageTo",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.paramSendTo.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.gString,
      if4031.generated.gInt> METHOD_EXIT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "if4031.Chat", "exit",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gInt.parser()));
  public static final io.grpc.MethodDescriptor<if4031.generated.gString,
      if4031.generated.Message> METHOD_RECEIVE_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          "if4031.Chat", "receiveMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.gString.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.generated.Message.parser()));

  public static ChatStub newStub(io.grpc.Channel channel) {
    return new ChatStub(channel);
  }

  public static ChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatBlockingStub(channel);
  }

  public static ChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatFutureStub(channel);
  }

  public static interface Chat {

    public void setNickname(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.gString> responseObserver);

    public void joinChannel(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver);

    public void leaveChannel(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver);

    public void sendMessage(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver);

    public void sendMessageTo(if4031.generated.paramSendTo request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver);

    public void exit(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver);

    public void receiveMessage(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.Message> responseObserver);
  }

  public static interface ChatBlockingClient {

    public if4031.generated.gString setNickname(if4031.generated.gString request);

    public if4031.generated.gInt joinChannel(if4031.generated.paramSend request);

    public if4031.generated.gInt leaveChannel(if4031.generated.paramSend request);

    public if4031.generated.gInt sendMessage(if4031.generated.paramSend request);

    public if4031.generated.gInt sendMessageTo(if4031.generated.paramSendTo request);

    public if4031.generated.gInt exit(if4031.generated.gString request);

    public java.util.Iterator<if4031.generated.Message> receiveMessage(
        if4031.generated.gString request);
  }

  public static interface ChatFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gString> setNickname(
        if4031.generated.gString request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> joinChannel(
        if4031.generated.paramSend request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> leaveChannel(
        if4031.generated.paramSend request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> sendMessage(
        if4031.generated.paramSend request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> sendMessageTo(
        if4031.generated.paramSendTo request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> exit(
        if4031.generated.gString request);
  }

  public static class ChatStub extends io.grpc.stub.AbstractStub<ChatStub>
      implements Chat {
    private ChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatStub(channel, callOptions);
    }

    @java.lang.Override
    public void setNickname(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.gString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void joinChannel(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void leaveChannel(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessage(if4031.generated.paramSend request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessageTo(if4031.generated.paramSendTo request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void exit(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void receiveMessage(if4031.generated.gString request,
        io.grpc.stub.StreamObserver<if4031.generated.Message> responseObserver) {
      asyncServerStreamingCall(
          channel.newCall(METHOD_RECEIVE_MESSAGE, callOptions), request, responseObserver);
    }
  }

  public static class ChatBlockingStub extends io.grpc.stub.AbstractStub<ChatBlockingStub>
      implements ChatBlockingClient {
    private ChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public if4031.generated.gString setNickname(if4031.generated.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public if4031.generated.gInt joinChannel(if4031.generated.paramSend request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.generated.gInt leaveChannel(if4031.generated.paramSend request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public if4031.generated.gInt sendMessage(if4031.generated.paramSend request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public if4031.generated.gInt sendMessageTo(if4031.generated.paramSendTo request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request);
    }

    @java.lang.Override
    public if4031.generated.gInt exit(if4031.generated.gString request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @java.lang.Override
    public java.util.Iterator<if4031.generated.Message> receiveMessage(
        if4031.generated.gString request) {
      return blockingServerStreamingCall(
          channel.newCall(METHOD_RECEIVE_MESSAGE, callOptions), request);
    }
  }

  public static class ChatFutureStub extends io.grpc.stub.AbstractStub<ChatFutureStub>
      implements ChatFutureClient {
    private ChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gString> setNickname(
        if4031.generated.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SET_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> joinChannel(
        if4031.generated.paramSend request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> leaveChannel(
        if4031.generated.paramSend request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> sendMessage(
        if4031.generated.paramSend request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> sendMessageTo(
        if4031.generated.paramSendTo request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE_TO, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.generated.gInt> exit(
        if4031.generated.gString request) {
      return futureUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Chat serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("if4031.Chat")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SET_NICKNAME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.gString,
                if4031.generated.gString>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.gString request,
                  io.grpc.stub.StreamObserver<if4031.generated.gString> responseObserver) {
                serviceImpl.setNickname(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.paramSend,
                if4031.generated.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.paramSend request,
                  io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
                serviceImpl.joinChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.paramSend,
                if4031.generated.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.paramSend request,
                  io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
                serviceImpl.leaveChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.paramSend,
                if4031.generated.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.paramSend request,
                  io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE_TO,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.paramSendTo,
                if4031.generated.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.paramSendTo request,
                  io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
                serviceImpl.sendMessageTo(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_EXIT,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                if4031.generated.gString,
                if4031.generated.gInt>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.gString request,
                  io.grpc.stub.StreamObserver<if4031.generated.gInt> responseObserver) {
                serviceImpl.exit(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_RECEIVE_MESSAGE,
          asyncServerStreamingCall(
            new io.grpc.stub.ServerCalls.ServerStreamingMethod<
                if4031.generated.gString,
                if4031.generated.Message>() {
              @java.lang.Override
              public void invoke(
                  if4031.generated.gString request,
                  io.grpc.stub.StreamObserver<if4031.generated.Message> responseObserver) {
                serviceImpl.receiveMessage(request, responseObserver);
              }
            }))).build();
  }
}
