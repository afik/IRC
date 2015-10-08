
package if4031.model;

import if4031.generated.Message;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class User {
    private final String nickName;
    private ArrayList<String> channels;
    private ArrayList<Message> messages;
    public User(String nickname){
        this.nickName = nickname;
        channels = new ArrayList<>();
        messages = new ArrayList<>();
    }
    public String getNickName(){
        return nickName;
    }
    public void joinChannel(String channel){
        if(isJoinChannel(channel)){
            
        }
        else channels.add(channel);
    }
    public void leaveChannel(String channel){
        channels.remove(channel);
    }
    public boolean isJoinChannel(String channel){
        if(channels.isEmpty()) return false;
        else return channels.contains(channel);
    }
    public List<String> getChannels(){
        return channels;
    }
    public void saveMessage(Message message){
        Message m = Message.newBuilder(message).build();
        messages.add(m);
        System.out.println("Message added");
    }
    public List<Message> getMessage(){
        List<Message> messagesTemp = new ArrayList<>();
        Message m;
        System.out.println("Message size : " + messages.size());
        for(Message message : messages){
            m = Message.newBuilder(message).build();
            System.out.println("Messages : " + m.getMessage());
            messagesTemp.add(m);
        }
        messages.clear();
        return messagesTemp;
    }
}
