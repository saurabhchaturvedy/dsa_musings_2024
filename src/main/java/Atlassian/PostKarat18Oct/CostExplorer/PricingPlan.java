package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;

public class PricingPlan {
    private String planId;
    private BigDecimal monthlyCost;

    // Constructors, getters, and setters
    public PricingPlan(String planId, BigDecimal monthlyCost) {
        this.planId = planId;
        this.monthlyCost = monthlyCost;
    }

    public String getPlanId() {
        return planId;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }
}