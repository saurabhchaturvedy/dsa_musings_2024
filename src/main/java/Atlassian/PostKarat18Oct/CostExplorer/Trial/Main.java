package Atlassian.PostKarat18Oct.CostExplorer.Trial;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        Subscription trialSubscription = new Subscription(PricingPlan.TRIAL, LocalDate.parse("2021-01-01"),
                LocalDate.parse("2021-01-30"), PricingPlan.BASIC);
        Customer customer = new Customer("c1", "Jira", trialSubscription);
        CostExplorer costExplorer = new CostExplorer(customer);

        // Get monthly costs
        BigDecimal[] monthlyCosts = costExplorer.monthlyCostList();
        System.out.println("Monthly Costs: " + Arrays.toString(monthlyCosts));

        // Get annual cost
        BigDecimal annualCost = costExplorer.annualCost();
        System.out.println("Annual Cost: " + annualCost);


    }
}

