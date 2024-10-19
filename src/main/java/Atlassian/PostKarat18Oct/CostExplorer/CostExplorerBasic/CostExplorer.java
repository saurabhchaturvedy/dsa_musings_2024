package Atlassian.PostKarat18Oct.CostExplorer.CostExplorerBasic;

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

        BigDecimal[] monthlyCosts = new BigDecimal[12];
        Arrays.fill(monthlyCosts, BigDecimal.ZERO);

        Subscription subscription = customer.getSubscription();
        PricingPlan pricingPlan = subscription.getPlanId();
        LocalDate startDate = subscription.getStartDate();
        BigDecimal monthlyCost = (pricingPlan != null) ? pricingPlan.getMonthlyCost() : BigDecimal.ZERO;

        if (PricingPlan.TRIAL.equals(pricingPlan)) {


            LocalDate endDate = subscription.getEndDate();

            Period trialPeriod = Period.between(startDate, endDate);

            long totalMonths = trialPeriod.toTotalMonths() + 1;

            for (int i = 0; i < totalMonths; i++) {

                int index = startDate.getMonthValue() - 1;
                monthlyCosts[index] = pricingPlan.getMonthlyCost();
                startDate = startDate.plusMonths(1);
            }


            PricingPlan planPostTrialOver = subscription.getPlanPostTrialOver();

            BigDecimal mCost = (planPostTrialOver != null) ? planPostTrialOver.getMonthlyCost() : BigDecimal.ZERO;

            for (int i = startDate.getMonthValue() - 1; i < 12; i++) {

                monthlyCosts[i] = mCost;
            }


        } else {

            LocalDate endDate = LocalDate.of(startDate.getYear(), Month.DECEMBER, 31);
            Period subscribedMonths = Period.between(startDate, endDate);
            long totalMonths = subscribedMonths.toTotalMonths() + 1;

            for (int i = 0; i < totalMonths; i++) {
                int index = startDate.getMonthValue() - 1;
                monthlyCosts[index] = monthlyCost;
                startDate = startDate.plusMonths(1);
            }
        }

        return monthlyCosts;
    }

    @Override
    public BigDecimal annualCost() {
        return Arrays.stream(monthlyCostList()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
