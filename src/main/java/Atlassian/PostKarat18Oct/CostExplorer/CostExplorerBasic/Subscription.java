package Atlassian.PostKarat18Oct.CostExplorer.CostExplorerBasic;

import java.time.LocalDate;

public class Subscription {


    PricingPlan planId;

    LocalDate startDate;
    LocalDate endDate; // for TRIAL subscription

    PricingPlan planAfterTrialEnds;


    public Subscription(PricingPlan planId, LocalDate startDate, LocalDate endDate, PricingPlan planAfterTrialEnds) {
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.planAfterTrialEnds = planAfterTrialEnds;
    }


    public PricingPlan getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public PricingPlan getPlanPostTrialOver() {
        return planAfterTrialEnds;
    }
}
