package DesignPatterns.Structural.Decorator;

abstract class PlanDecorator implements MobilePlan {
    protected MobilePlan mobilePlan;

    public PlanDecorator(MobilePlan mobilePlan) {
        this.mobilePlan = mobilePlan;
    }
}