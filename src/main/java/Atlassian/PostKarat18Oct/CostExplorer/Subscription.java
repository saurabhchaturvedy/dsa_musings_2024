package Atlassian.PostKarat18Oct.CostExplorer;

import java.time.LocalDate;

class Subscription {
    private String planId;
    private LocalDate startDate;

    // Constructors, getters, and setters
    public Subscription(String planId, LocalDate startDate) {
        this.planId = planId;
        this.startDate = startDate;
    }

    public String getPlanId() {
        return planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}