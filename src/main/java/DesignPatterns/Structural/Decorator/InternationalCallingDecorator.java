package DesignPatterns.Structural.Decorator;

class InternationalCallingDecorator extends PlanDecorator {
    public InternationalCallingDecorator(MobilePlan mobilePlan) {
        super(mobilePlan);
    }

    @Override
    public String getDescription() {
        return mobilePlan.getDescription() + ", International Calling";
    }

    @Override
    public double cost() {
        return mobilePlan.cost() + 7.00; // Add cost for international calling
    }
}