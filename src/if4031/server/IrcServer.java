package if4031.server;

import if4031.ChatGrpc;
import if4031.ChatTool;
import if4031.generated.Message;
import if4031.generated.gInt;
import if4031.generated.gString;
import if4031.generated.paramSend;
import if4031.generated.paramSendTo;
import if4031.model.Channel;
import if4031.model.User;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class IrcServer {
    private static final Logger logger = Logger.getLogger(IrcServer.class.getName());

    private static int port = 8980;
    private ServerImpl grpcServer;
    
    public IrcServer(int port) {
        this.port = port;
    }
    
    /** Start serving requests. */
    public void start() throws IOException {
        grpcServer = NettyServerBuilder.forPort(port)
                .addService(ChatGrpc.bindService(new ChatService()))
                .build().start();
        logger.info("Server started, listening on " + port);
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
            public void run() {
                // Use stderr here since the logger may has been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                IrcServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (grpcServer != null) {
          grpcServer.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        IrcServer server = new IrcServer(8980);
        server.start();
        
    }
    
    private static class ChatService implements ChatGrpc.Chat{
                
        ChatService() {
            ChatTool tool = new ChatTool();
        }
        
        @Override
        public void setNickname(gString request, StreamObserver<gString> responseObserver) {
            Date date = new Date(System.currentTimeMillis());
            String nickname = request.getVal();
            // check if any user use this nickname. if exist, generate random nickname
            if(ChatTool.isNicknameExist(nickname)){
                nickname = ChatTool.generateRandomNickName();
            }else {System.out.println("No nickname");}
            User user = new User(nickname);
            ChatTool.addUser(user);
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + " NICKNAME " + nickname);
            responseObserver.onValue(gString.newBuilder().setVal(nickname).build());
            responseObserver.onCompleted();
        }

        @Override
        public void joinChannel(paramSend request, StreamObserver<gInt> responseObserver) {
            String nickname = request.getParam1();
            String channel = request.getParam2();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " JOIN " + channel );
            User user;
            user = ChatTool.getUser(nickname);
            Channel c;
            if(ChatTool.isChannelExist(channel)){
                c = ChatTool.getChannel(channel);
            }
            else {
                c = new Channel(channel);
                ChatTool.addChannel(c);
            }
            user.joinChannel(channel); // bagaimana jika channel tsb udah dia join?
            c.userJoin(user.getNickName());
            
            responseObserver.onValue(gInt.newBuilder().setVal(1).build());
            responseObserver.onCompleted();
        }

        @Override
        public void leaveChannel(paramSend request, StreamObserver<gInt> responseObserver) {
            String nickname = request.getParam1();
            String channel = request.getParam2();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " LEAVE " + channel );
            User user = ChatTool.getUser(nickname);
            Channel c;
            if(ChatTool.isChannelExist(channel)){
                c = ChatTool.getChannel(channel);
                user.leaveChannel(channel);
                c.userLeave(user.getNickName());
                // if the channel has 0 member, delete the channel
                responseObserver.onValue(gInt.newBuilder().setVal(1).build());
            }
            else {
                responseObserver.onValue(gInt.newBuilder().setVal(-1).build());
            }
            responseObserver.onCompleted();
        }

        @Override
        public void sendMessage(paramSend request, StreamObserver<gInt> responseObserver) {
            String nickname = request.getParam1();
            String message = request.getParam2();
            Date date = new Date(System.currentTimeMillis());
            User user = ChatTool.getUser(nickname);
            Channel channel;
            for(String c : user.getChannels()){
                channel = ChatTool.getChannel(c);
                channel.sendMessagetoMember(nickname, message);
            }
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " SEND MESSAGE");
            responseObserver.onValue(gInt.newBuilder().setVal(1).build());
            responseObserver.onCompleted();
        }

        @Override
        public void sendMessageTo(paramSendTo request, StreamObserver<gInt> responseObserver) {
            String nickname = request.getParam1();
            String channel = request.getParam2();
            String message = request.getParam3();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " SEND MESSAGE TO " + channel);
            if(ChatTool.isChannelExist(channel)){
                Channel channelC = ChatTool.getChannel(channel);
                if(channelC.isMember(nickname)){
                    channelC.sendMessagetoMember(nickname, message);
                    System.out.println("Message added");
                    responseObserver.onValue(gInt.newBuilder().setVal(1).build());
                } else {
                    responseObserver.onValue(gInt.newBuilder().setVal(-1).build());
                }
            } else {
                responseObserver.onValue(gInt.newBuilder().setVal(0).build());
            }
            responseObserver.onCompleted();
        }

        @Override
        public void exit(gString request, StreamObserver<gInt> responseObserver) {
            String nickname = request.getVal();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " EXIT");
            User user = ChatTool.getUser(nickname);
            List<String> channels = user.getChannels();
            Channel c;
            for(String channel : channels){
                c = ChatTool.getChannel(channel);
                c.userLeave(user.getNickName());
            }
            ChatTool.removeUser(user);
            responseObserver.onValue(gInt.newBuilder().setVal(1).build());
            responseObserver.onCompleted();
        }

        @Override
        public void receiveMessage(gString request, StreamObserver<Message> responseObserver) {
            String nickname = request.getVal();
            Date date = new Date(System.currentTimeMillis());
            System.out.println(
                    "[" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate() + "] "
                            + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + nickname + " RECEIVE MESSAGE");
            User user = ChatTool.getUser(nickname);
            List<Message> messages = user.getMessage();
            
            for (Message message : messages) {
                responseObserver.onValue(message);
            }
            
            responseObserver.onCompleted();
        }

        
        
    }
}
