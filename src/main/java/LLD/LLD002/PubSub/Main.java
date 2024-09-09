package LLD.LLD002.PubSub;

public class Main {


    public static void main(String[] args) {


        Topic t1 = new Topic("T1");
        Topic t2 = new Topic("T2");
        Topic t3 = new Topic("T3");


        Publisher p1 = new Publisher("P1");
        Publisher p2 = new Publisher("P2");
        Publisher p3 = new Publisher("P3");

        Subscriber s1 = new PrintSubscriber("S1");
        Subscriber s2 = new PrintSubscriber("S2");
        Subscriber s3 = new PrintSubscriber("S3");

        p1.registerTopic(t1);

        t1.addSubscriber(s1);
        t1.addSubscriber(s2);
        t1.addSubscriber(s3);

        p1.publish(t1, new Message(" Hola !!"));

        t1.removeSubscriber(s2);

        System.out.println();

        p1.publish(t1, new Message(" Hola !!"));
    }
}
