package DesignPatterns.Structural.Decorator;

class ExtraDataDecorator extends PlanDecorator {
    public ExtraDataDecorator(MobilePlan mobilePlan) {
        super(mobilePlan);
    }

    @Override
    public String getDescription() {
        return mobilePlan.getDescription() + ", Extra 5GB Data";
    }

    @Override
    public double cost() {
        return mobilePlan.cost() + 5.00; // Add cost for extra data
    }
}