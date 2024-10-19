package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CostExplorer {
    private List<PricingPlan> pricingPlans = Arrays.asList(
            new PricingPlan("BASIC", new BigDecimal("9.99")),
            new PricingPlan("STANDARD", new BigDecimal("49.99")),
            new PricingPlan("PREMIUM", new BigDecimal("249.99"))
    );

    private Customer customer;

    public CostExplorer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal[] monthlyCostList() {
        BigDecimal[] costs = new BigDecimal[12];
        Arrays.fill(costs, BigDecimal.ZERO);

        LocalDate startDate = customer.getSubscription().getStartDate();
        String planId = customer.getSubscription().getPlanId();

        // Get the monthly cost based on the plan ID
        BigDecimal monthlyCost = pricingPlans.stream()
                .filter(plan -> plan.getPlanId().equals(planId))
                .map(PricingPlan::getMonthlyCost)
                .findFirst()
                .orElse(BigDecimal.ZERO);

        // Calculate costs for each month
        for (int month = 0; month < 12; month++) {
            LocalDate currentMonth = LocalDate.of(startDate.getYear(), Month.values()[month], 1);
            if (currentMonth.isEqual(startDate) || currentMonth.isAfter(startDate)) {
                costs[month] = monthlyCost;
            }
        }

        return costs;
    }

    public BigDecimal annualCost() {
        return Arrays.stream(monthlyCostList())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
