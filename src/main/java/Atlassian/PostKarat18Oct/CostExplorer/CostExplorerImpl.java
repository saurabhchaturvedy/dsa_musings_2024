package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class CostExplorerImpl implements CostExplorer {
    private Customer customer;

    public CostExplorerImpl(Customer customer) {
        this.customer = customer;
    }

    @Override
    public BigDecimal[] monthlyCostList() {
        BigDecimal[] costs = new BigDecimal[12];
        Arrays.fill(costs, BigDecimal.ZERO);

        LocalDate startDate = customer.getSubscription().getStartDate();
        PricingPlan pricingPlan = customer.getSubscription().getPlanId();

        // Get the monthly cost based on the plan ID

        BigDecimal monthlyCost = (pricingPlan != null) ? pricingPlan.getMonthlyCost() : BigDecimal.ZERO;

        // Calculate costs for each month
        for (int month = 0; month < 12; month++) {
            LocalDate currentMonth = LocalDate.of(startDate.getYear(), Month.values()[month], 1);
            if (currentMonth.isEqual(startDate) || currentMonth.isAfter(startDate)) {
                costs[month] = monthlyCost;
            }
        }

        return costs;
    }

    @Override
    public BigDecimal annualCost() {
        return Arrays.stream(monthlyCostList())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}