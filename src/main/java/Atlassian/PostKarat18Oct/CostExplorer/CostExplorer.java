package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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
            // Handle trial subscriptions
            LocalDate endDate = subscription.getEndDate();
            // Calculate the total months of trial period
            Period trialPeriod = Period.between(startDate, endDate);
            long trialMonths = trialPeriod.toTotalMonths();

            // Fill monthly costs for the trial period
            for (int i = 0; i < trialMonths; i++) {
                int monthIndex = startDate.getMonthValue() - 1; // Month is 1-12
                monthlyCosts[monthIndex] = BigDecimal.ZERO; // Trial cost is 0
                startDate = startDate.plusMonths(1);
            }

            // Switch to the new plan after the trial period
            PricingPlan planAfterTrial = subscription.getPlanAfterTrial();
            monthlyCost = (planAfterTrial != null) ? planAfterTrial.getMonthlyCost() : BigDecimal.ZERO;

            // Calculate remaining months for the year
            for (int month = startDate.getMonthValue() - 1; month < 12; month++) {
                monthlyCosts[month] = monthlyCost;
            }
        } else {
            // Handle regular subscriptions
            // Calculate the end of the year from the start date
            LocalDate endOfYear = LocalDate.of(startDate.getYear(), Month.DECEMBER, 31);
            Period subscriptionPeriod = Period.between(startDate, endOfYear);
            long totalMonths = subscriptionPeriod.toTotalMonths() + 1; // Including the start month

            // Fill monthly costs for the subscription duration
            for (int month = 0; month < totalMonths; month++) {
                int monthIndex = startDate.getMonthValue() - 1; // Month is 1-12
                monthlyCosts[monthIndex] = monthlyCost;
                startDate = startDate.plusMonths(1);
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
