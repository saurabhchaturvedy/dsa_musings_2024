package LLD.LLD002.PubSub;

import java.util.HashSet;
import java.util.Set;

public class Publisher {


    String name;


    Set<Topic> topics;


    Publisher(String name) {

        this.name = name;
        this.topics = new HashSet<>();
    }


    public void registerTopic(Topic topic) {

        topics.add(topic);
    }


    public void publish(Topic topic, Message message) {


        if (!topics.contains(topic)) {

            System.out.println(" cannot publish message to the topic : " + topic.name);
        }

        topic.publish(message);
    }
}
