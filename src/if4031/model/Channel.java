package if4031.model;

import if4031.ChatTool;
import if4031.generated.Message;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Channel {
    private final String channelName;
    private ArrayList<String> users;
    public Channel(String channelName){
        this.channelName = channelName;
        users = new ArrayList<>();
    }
    public String getChannelName(){
        return channelName;
    }
    public void userJoin(String nickName){
        users.add(nickName);
    }
    public void userLeave(String nickName){
        users.remove(nickName);
    }
    public void sendMessagetoMember(String nickSource, String message){
        User user;
        Message messageM = Message.newBuilder()
                            .setChannel(channelName)
                            .setMessage(message)
                            .setNickname(nickSource)
                            .setTime(null)
                            .build();
        for(String nick : users){
            user = ChatTool.getUser(nick);
            user.saveMessage(messageM);
        }
    }
    public boolean isMember(String user){
        return users.contains(user);
    }
}
