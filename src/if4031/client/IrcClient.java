package if4031.client;

import if4031.ChatGrpc;
import if4031.ChatGrpc.ChatBlockingStub;
import if4031.generated.Message;
import if4031.generated.gString;
import if4031.generated.paramSend;
import if4031.generated.paramSendTo;
import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IrcClient {
    private static final Logger logger = Logger.getLogger(IrcClient.class.getName());

    private static ChannelImpl channel;
    private static ChatBlockingStub blockingStub;
    private static String nickname;
    private static int alive; // 0 for started, 1 for nickname has set, -1 for exit
    
    public IrcClient(String host, int port) {
      channel = NettyChannelBuilder.forAddress(host, port)
          .negotiationType(NegotiationType.PLAINTEXT)
          .build();
      blockingStub = ChatGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
      channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    
    public static void main(String[] args) throws Exception {
        IrcClient client = new IrcClient("localhost", 8980);

        try {
            nickname = null;
            alive = 0;
            Scanner in = new Scanner(System.in);
            String command;

            while (alive != -1) {
                command = in.nextLine();
                client.sendCommand(command);    
            }

        } finally {
          client.shutdown();
        }
    }
    
    /*
     * Call service method based on what user's input
     * @param String
     */
    private void sendCommand(String command) {
        String[] parsed = command.split(" ");
        
        switch(parsed[0]){
            case "/NICK" : {
                if (nickname != null) {
                    blockingStub.exit(gString.newBuilder().setVal(nickname).build());
                }
                gString parsed1 = gString.newBuilder().setVal(parsed[1]).build();
                nickname = blockingStub.setNickname(parsed1).getVal();
                System.out.println("Your nickname is " + nickname);
                alive = 1;                    
                break;
            }
            case "/JOIN" : {
                if(nickname != null){
                    try {
                        if(blockingStub.joinChannel(paramSend.newBuilder()
                                            .setParam1(nickname)
                                            .setParam2(parsed[1])
                                            .build()).getVal() == 1){
                            System.out.println("You joined " + parsed[1]);
                            receive();
                        }
                    } catch (RuntimeException e) {
                        logger.log(Level.WARNING, "RPC failed", e);
                    } 
                }
                break;
            }
            case "/LEAVE" : {
                try{
                    if(nickname != null){
                        if(blockingStub.leaveChannel(paramSend.newBuilder()
                                            .setParam1(nickname)
                                            .setParam2(parsed[1])
                                            .build()).getVal() == 1){
                            System.out.println("You left " + parsed[1]);
                            receive();
                        }
                    }
                } catch (RuntimeException e) {
                    logger.log(Level.WARNING, "RPC failed", e);
                }
                break;
            }
            case "/EXIT" : {
                try {
                    if(nickname != null){
                        if(blockingStub.exit(gString.newBuilder().setVal(nickname).build())
                                 .getVal() == 1){
                            alive = -1;
                            System.out.println("You exit ");
                        }
                    }
                } catch (RuntimeException e) {
                    logger.log(Level.WARNING, "RPC failed", e);
                }
                break;
            }
            default : {
                try {
                    if(nickname != null){
                        String message;
                        if(parsed[0].startsWith("@")){
                            if(parsed.length > 1){
                                String channel = parsed[0].replaceFirst("@", "");
                                message = parsed[1];
                                if(parsed.length > 2){
                                    for(int i = 2; i < parsed.length; i++){
                                        message = message + " " + parsed[i];
                                    }
                                }
                                if(blockingStub.sendMessageTo(paramSendTo.newBuilder()
                                            .setParam1(nickname)
                                            .setParam2(channel)
                                            .setParam3(message)
                                            .build()).getVal() == 1){}
                            }
                        }
                        else{
                            message = parsed[0];
                            if(parsed.length >= 2){
                                for(int i = 1; i < parsed.length; i++){
                                    message = message + " " + parsed[i];
                                }
                            }
                            if(blockingStub.sendMessage(paramSend.newBuilder()
                                            .setParam1(nickname)
                                            .setParam2(message)
                                            .build()).getVal() == 1){}
                        }
                        receive();
                    }
                } catch (RuntimeException e) {
                    logger.log(Level.WARNING, "RPC failed", e);
                }
            }
        }
    }
    
    private void receive() {
        Iterator<Message> messages = blockingStub.receiveMessage(gString.newBuilder().setVal(nickname).build());

        List<Message> listMessages = new ArrayList<Message>();

        while(messages.hasNext()) {
            Message m = messages.next();
            listMessages.add(m);
        }

        if(!listMessages.isEmpty()){
            for(Message message : listMessages){
                if(message.getChannel() != null && message.getMessage() != null && message.getNickname() != null){
                    System.out.println("[" + message.getChannel() + "] (" 
                                           + message.getNickname() 
                                           + ") " + message.getMessage());
                }
            }
        }
    }
}
