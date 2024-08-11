package DesignPatterns.Structural.Decorator;

class MusicStreamingDecorator extends PlanDecorator {
    public MusicStreamingDecorator(MobilePlan mobilePlan) {
        super(mobilePlan);
    }

    @Override
    public String getDescription() {
        return mobilePlan.getDescription() + ", Music Streaming Service";
    }

    @Override
    public double cost() {
        return mobilePlan.cost() + 3.00; // Add cost for music streaming
    }
}