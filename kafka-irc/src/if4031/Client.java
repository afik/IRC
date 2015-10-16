
package if4031;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 *
 * @author TOLEP
 */
public class Client {
    public static String nickname;
    public kafka.javaapi.producer.Producer<String,String> producer;
    public static Map<String, Consumer> consumerMap;
    public static boolean alive;
        
    public class Consumer implements Runnable {
        private final ConsumerConnector consumer;
        private final String zooKeeper = "localhost:2181";
        private final String topic;
        
        public Consumer(String a_groupId, String topic) {
            consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                    createConsumerConfig(zooKeeper, a_groupId));
            this.topic = topic;
        }
        
        @Override
        public void run() {
            System.out.println("Consumer start listening");
            
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(topic, new Integer(1));
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

            ConsumerIterator<byte[], byte[]> it = streams.get(0).iterator();
            while (it.hasNext()) {
                System.out.println(new String(it.next().message()));
            }               
        }

        public void shutdown() {
            consumer.shutdown();
            
        }

        private ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
            Properties props = new Properties();
            props.put("zookeeper.connect", a_zookeeper);
            props.put("group.id", a_groupId);
            props.put("zookeeper.session.timeout.ms", "400");
            props.put("auto.commit.interval.ms", "1000");

            return new ConsumerConfig(props);
        }
    }
    
    public static void main(String[] argv){
        Client client = new Client();
        client.nickname = "";
        client.consumerMap = new HashMap<>();
        client.alive = true;
        
        while(client.alive){
            String command = client.receiveCommand();
            client.handleCommand(command);
        }
        
    }
    
    public Client(){
        
    }
    
    public void handleCommand(String command){
        String[] parsed = parsing(command);
        if(parsed.length > 1)
            switch(parsed[0]){
                case "/NICK":{
                    if(nickname.equals("")){
                        nickname = checkNickname(parsed[1]); //see TODO below
                        System.out.println("Your nickname : " + nickname);
                        producer = new kafka.javaapi.producer.Producer<>(getProducerConfig());
                    } else {
                        //TODO logout and set new nick
                    }
                    break;
                }
                case "/JOIN":{
                    if(parsed.length > 1){
                        String channel = parsed[1];
                        if (consumerMap.containsKey(channel)) {
                            System.out.println("You already joined " + channel + " channel.");
                        } else {
                            Consumer consume = new Consumer(nickname, channel);
                            new Thread(consume).start();
                            consumerMap.put(channel, consume);
                            System.out.println("You successfully joined " + channel);
                        }
                    }
                    break;
                }
                case "/LEAVE":{
                    if (parsed.length > 1) {
                        String channel = parsed[1];
                        if (!consumerMap.containsKey(channel)) {
                            System.out.println("You aren't member of " + channel);
                        } else {
                            Consumer cons = consumerMap.get(channel);
                            cons.shutdown();
                            consumerMap.remove(channel);
                            System.out.println("You leave " + channel);
                        }
                    }
                    break;
                }
                default:{
                    KeyedMessage<String, String> message = null;
                    if(command.startsWith("@")){
                        if(parsed.length > 1){
                            String[] channel = parsed[0].split("@");
                            String msg = "["+channel[1]+"] ("+nickname+") "+parsed[1];
                            message = new KeyedMessage<>(channel[1],msg);
                            producer.send(message);
                        }
                    }
                    else{
                        for(String channel: consumerMap.keySet()){
                            String msg = "["+channel+"] ("+nickname+") "+command;
                            message = new KeyedMessage<>(channel,msg);
                            producer.send(message);
                        }
                    }
                }
            }
        else if(command.equals("/EXIT")){
            for (Consumer c : consumerMap.values()) {
                c.shutdown();
            }

            System.out.println("Bye....");
            producer.close();
            alive = false;
        }
        else if(!parsed[0].equals("")){
            KeyedMessage<String, String> message = null;
            for(String channel: consumerMap.keySet()){
                String msg = "["+channel+"] ("+nickname+") "+command;
                message = new KeyedMessage<>(channel,msg);
                producer.send(message);
            }
        }
    }
    
    public String receiveCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    
    public static String checkNickname(String nick) {
        //TODO : if exist generate new random
        return nick;
    }
    
    public static String[] parsing(String command){
        String [] split = command.split(" ", 2);
        return split;
        
    }
    
    public static ProducerConfig getProducerConfig(){
        Properties prop = new Properties();
        prop.put("metadata.broker.list","localhost:9092");
        prop.put("serializer.class","kafka.serializer.StringEncoder");
        ProducerConfig producerConfig = new ProducerConfig(prop);
        return producerConfig;
    }
    
}

