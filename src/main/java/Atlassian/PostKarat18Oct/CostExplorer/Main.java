package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Subscription trialSubscription = new Subscription(PricingPlan.TRIAL, LocalDate.parse("2021-01-01"),
                LocalDate.parse("2021-01-30"), PricingPlan.BASIC);

        Customer customer = new Customer("c1", "Jira", trialSubscription);
        CostExplorerBasic costExplorer = new CostExplorerBasic(customer); // Using the interface reference

        // Get monthly costs
        BigDecimal[] monthlyCosts = costExplorer.monthlyCostList();
        System.out.println("Monthly Costs: " + Arrays.toString(monthlyCosts));

        // Get annual cost
        BigDecimal annualCost = costExplorer.annualCost();
        System.out.println("Annual Cost: " + annualCost);



        Subscription regularSubscription = new Subscription(PricingPlan.BASIC, LocalDate.parse("2021-05-15"),
                null, null);
        Customer customer2 = new Customer("c1", "Jira", regularSubscription);
        CostExplorerBasic costExplorer2 = new CostExplorerBasic(customer2); // Using the interface reference

        // Get monthly costs
        BigDecimal[] monthlyCosts2 = costExplorer2.monthlyCostList();
        System.out.println("Monthly Costs: " + Arrays.toString(monthlyCosts2));

        // Get annual cost
        BigDecimal annualCost2 = costExplorer.annualCost();
        System.out.println("Annual Cost: " + annualCost2);
    }
}

