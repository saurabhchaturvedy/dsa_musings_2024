package Atlassian.PostKarat18Oct.CostExplorer.Trial;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

public class CostExplorer implements ICostExplorer {
    private Customer customer;

    public CostExplorer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public BigDecimal[] monthlyCostList() {
        BigDecimal[] monthlyCosts = new BigDecimal[12];
        Arrays.fill(monthlyCosts, BigDecimal.ZERO);

        Subscription subscription = customer.getSubscription();
        LocalDate startDate = subscription.getStartDate();
        PricingPlan pricingPlan = subscription.getPlanId();


        BigDecimal monthlyCost = (pricingPlan != null) ? pricingPlan.getMonthlyCost() : BigDecimal.ZERO;

        if (pricingPlan.equals(PricingPlan.TRIAL)) {
            // Calculate costs during the trial period
            LocalDate endDate = subscription.getEndDate();
            LocalDate currentDate = startDate;

            while (currentDate.isBefore(endDate) || currentDate.isEqual(endDate)) {
                int monthIndex = currentDate.getMonthValue() - 1; // Month is 1-12
                monthlyCosts[monthIndex] = monthlyCost; // Trial is free, so it's 0
                currentDate = currentDate.plusMonths(1);
            }

            // After trial, switch to the new plan
            PricingPlan planAfterTrial = subscription.getPlanAfterTrial();
            PricingPlan postTrialPlan = planAfterTrial;
            monthlyCost = (postTrialPlan != null) ? postTrialPlan.getMonthlyCost() : BigDecimal.ZERO;

            // Calculate costs for remaining months after trial ends
            while (currentDate.getYear() == startDate.getYear()) {
                int monthIndex = currentDate.getMonthValue() - 1; // Month is 1-12
                monthlyCosts[monthIndex] = monthlyCost;
                currentDate = currentDate.plusMonths(1);
            }
        } else {
            // Regular subscription costs calculation
            for (int month = 0; month < 12; month++) {
                LocalDate currentMonth = LocalDate.of(startDate.getYear(), Month.values()[month], 1);
                if (currentMonth.isEqual(startDate) || currentMonth.isAfter(startDate)) {
                    monthlyCosts[month] = monthlyCost;
                }
            }
        }

        // Calculate annual cost

        return monthlyCosts;
    }


    @Override
    public BigDecimal annualCost() {
        return Arrays.stream(monthlyCostList())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
