package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Subscription subscription = new Subscription("BASIC", LocalDate.parse("2021-01-01"));
        Customer customer = new Customer("c1", "Jira", subscription);
        CostExplorer costExplorer = new CostExplorer(customer);

        // Get monthly costs
        BigDecimal[] monthlyCosts = costExplorer.monthlyCostList();
        System.out.println("Monthly Costs: " + Arrays.toString(monthlyCosts));

        // Get annual cost
        BigDecimal annualCost = costExplorer.annualCost();
        System.out.println("Annual Cost: " + annualCost);
    }
}
