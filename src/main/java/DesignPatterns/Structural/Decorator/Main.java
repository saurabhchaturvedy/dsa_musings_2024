package DesignPatterns.Structural.Decorator;

public class Main {
    public static void main(String[] args) {
        MobilePlan myPlan = new BasicPlan();
        System.out.println(myPlan.getDescription() + " $" + myPlan.cost());

        // Adding Extra Data
        myPlan = new ExtraDataDecorator(myPlan);
        System.out.println(myPlan.getDescription() + " $" + myPlan.cost());

        // Adding International Calling
        myPlan = new InternationalCallingDecorator(myPlan);
        System.out.println(myPlan.getDescription() + " $" + myPlan.cost());

        // Adding Music Streaming Service
        myPlan = new MusicStreamingDecorator(myPlan);
        System.out.println(myPlan.getDescription() + " $" + myPlan.cost());
    }
}
