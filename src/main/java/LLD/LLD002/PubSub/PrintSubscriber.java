package LLD.LLD002.PubSub;

public class PrintSubscriber implements Subscriber {


    String name;

    PrintSubscriber(String name) {

        this.name = name;
    }

    @Override
    public void onMessage(Message message) {

        System.out.println(" Subscriber " + name + " received message : " + message.getContent());
    }
}
