package Atlassian.PostKarat20Oct.CostExplorer;

import java.math.BigDecimal;

public enum PricingPlan {

    TRIAL("TRIAL", new BigDecimal("0.00")),
    BASIC("BASIC", new BigDecimal("9.99")),
    STANDARD("STANDARD", new BigDecimal("49.99")),
    PREMIUM("PREMIUM", new BigDecimal("249.99"));


    String planId;

    BigDecimal monthlyCost;

    PricingPlan(String planId, BigDecimal monthlyCost) {

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
