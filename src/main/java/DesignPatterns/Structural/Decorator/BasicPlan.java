package DesignPatterns.Structural.Decorator;

class BasicPlan implements MobilePlan {
    @Override
    public String getDescription() {
        return "Basic Plan (100 mins, 500 texts, 2GB data)";
    }

    @Override
    public double cost() {
        return 10.00; // Base cost for the basic plan
    }
}
