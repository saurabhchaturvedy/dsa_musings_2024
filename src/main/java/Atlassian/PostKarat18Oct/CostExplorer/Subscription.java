package Atlassian.PostKarat18Oct.CostExplorer;

import java.time.LocalDate;

class Subscription {
    private PricingPlan planId;
    private LocalDate startDate;

    // Constructors, getters, and setters
    public Subscription(PricingPlan planId, LocalDate startDate) {
        this.planId = planId;
        this.startDate = startDate;
    }

    public PricingPlan getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}