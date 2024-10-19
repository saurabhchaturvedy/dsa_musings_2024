package Atlassian.PostKarat18Oct.CostExplorer.CostExplorerBasic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {


        Subscription subscription = new Subscription(PricingPlan.TRIAL, LocalDate.parse("2021-01-01"), LocalDate.parse("2021-02-28"), PricingPlan.BASIC);

        Customer customer = new Customer("C1", "Jira", subscription);


        ICostExplorer costExplorer = new CostExplorer(customer);


        BigDecimal[] monthlyCosts = costExplorer.monthlyCostList();

        System.out.println(Arrays.toString(monthlyCosts));

        BigDecimal annualCost = costExplorer.annualCost();

        System.out.println(annualCost);


        Subscription subscription2 = new Subscription(PricingPlan.BASIC, LocalDate.parse("2021-01-01"), null, null);

        Customer customer2 = new Customer("C1", "Jira", subscription2);

        ICostExplorer costExplorer2 = new CostExplorer(customer2);
        BigDecimal[] monthlyCosts2 = costExplorer2.monthlyCostList();

        System.out.println(Arrays.toString(monthlyCosts2));

        BigDecimal annualCost2 = costExplorer2.annualCost();

        System.out.println(annualCost2);

    }
}
