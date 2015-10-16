/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package if4031;

import java.util.Scanner;
import kafka.producer.KeyedMessage;

/**
 *
 * @author user
 */
public class Producer {
    private kafka.javaapi.producer.Producer<String,String> producer;
    public String receiveCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    private String[] parsing(String command){
        return command.split(" ", 1);
    }
    private void runCommand(String command){
        String[] parsed = parsing(command);
        if(parsed.length > 1)
            switch(parsed[0]){
                case "/NICK":{
                    if(Tools.nickName.equals("")){
                        Tools.nickName = parsed[1];
                        producer = new kafka.javaapi.producer.Producer<String, String>(Tools.producerConfig);
                    }
                    break;
                }
                case "/JOIN":{
                    if(parsed.length > 1){
                        
                    }
                    break;
                }
                case "/LEAVE":{
                    break;
                }
                case "/EXIT":{
                    producer.close();
                    break;
                }
                default:{
                    KeyedMessage<String, String> message = null;
                    if(command.startsWith("@")){
                        if(parsed.length > 1){
                            String[] channel = parsed[0].split("@", 1);
                            message =new KeyedMessage<String, String>(channel[1],parsed[1]);
                        }
                    }
                    else{
                        for(String topic: Tools.listChannel){
                            message = new KeyedMessage<String, String>(topic,command);
                        }
                    }
                    producer.send(message);
                }
            }
    }
}
