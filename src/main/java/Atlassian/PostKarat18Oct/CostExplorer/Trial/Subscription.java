package Atlassian.PostKarat18Oct.CostExplorer.Trial;

import java.time.LocalDate;

public class Subscription {
    private PricingPlan planId;
    private LocalDate startDate;
    private LocalDate endDate; // For trial subscriptions
    private PricingPlan planAfterTrial; // Plan to switch to after trial

    public Subscription(PricingPlan planId, LocalDate startDate, LocalDate endDate, PricingPlan planAfterTrial) {
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planAfterTrial = planAfterTrial;
    }

    // Getters
    public PricingPlan getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public PricingPlan getPlanAfterTrial() {
        return planAfterTrial;
    }
}
