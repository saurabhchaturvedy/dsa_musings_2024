package Atlassian.PostKarat18Oct.CostExplorer.Trial;

import java.math.BigDecimal;

public class CostReport {
    private BigDecimal[] monthlyCosts;
    private BigDecimal annualCost;

    public CostReport(BigDecimal[] monthlyCosts, BigDecimal annualCost) {
        this.monthlyCosts = monthlyCosts;
        this.annualCost = annualCost;
    }

    public BigDecimal[] getMonthlyCosts() {
        return monthlyCosts;
    }

    public BigDecimal getAnnualCost() {
        return annualCost;
    }

    @Override
    public String toString() {
        return "CostReport{" +
                "monthlyCosts=" + monthlyCosts +
                ", annualCost=" + annualCost +
                '}';
    }
}
