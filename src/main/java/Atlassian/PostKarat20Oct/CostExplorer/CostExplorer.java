package Atlassian.PostKarat20Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;

public class CostExplorer implements ICostExplorer {


    Customer customer;

    CostExplorer(Customer customer) {

        this.customer = customer;
    }

    @Override
    public BigDecimal[] monthlyCostList() {

        BigDecimal[] monthlyCostLists = new BigDecimal[12];
        Arrays.fill(monthlyCostLists, BigDecimal.ZERO);

        Subscription subscription = customer.getSubscription();
        LocalDate startDate = subscription.getStartDate();
        PricingPlan planId = subscription.getPlanId();
        BigDecimal monthlyCost = planId != null ? planId.getMonthlyCost() : BigDecimal.ZERO;

        if (PricingPlan.TRIAL.equals(planId)) {

            LocalDate endDate = subscription.getEndDate();

            Period trialPeriod = Period.between(startDate, endDate);

            long trialPeriodMonths = trialPeriod.toTotalMonths() + 1;

            for (int i = 0; i < trialPeriodMonths; i++) {

                int index = startDate.getMonthValue() - 1;
                monthlyCostLists[index] = monthlyCost;
                startDate = startDate.plusMonths(1);
            }


            PricingPlan planAfterTrialEnds = subscription.getPlanAfterTrialEnds();
            BigDecimal monthlyCostPostTrial = planAfterTrialEnds != null ? planAfterTrialEnds.getMonthlyCost() : BigDecimal.ZERO;

            for (int i = startDate.getMonthValue() - 1; i < 12; i++) {

                monthlyCostLists[i] = monthlyCostPostTrial;

            }

        } else {

            LocalDate endDate = LocalDate.of(startDate.getYear(), Month.DECEMBER, 31);
            Period subscribedMonths = Period.between(startDate, endDate);
            long totalMonths = subscribedMonths.toTotalMonths() + 1;

            for (int i = 0; i < totalMonths; i++) {
                int index = startDate.getMonthValue() - 1;
                monthlyCostLists[index] = monthlyCost;
                startDate = startDate.plusMonths(1);
            }
        }

        return monthlyCostLists;
    }

    @Override
    public BigDecimal annualCost() {
        return Arrays.stream(monthlyCostList()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
