package Atlassian.PostKarat20Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        Subscription subscription = new Subscription(PricingPlan.TRIAL, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), PricingPlan.BASIC);

        Customer customer = new Customer("c1", "Jira", subscription);


        CostExplorer costExplorer = new CostExplorer(customer);


        BigDecimal[] bigDecimals = costExplorer.monthlyCostList();


        System.out.print(" TRIAL Plan :");
        System.out.println(Arrays.toString(bigDecimals));


        Subscription subscription2 = new Subscription(PricingPlan.BASIC, LocalDate.parse("2023-01-01"), null, null);

        Customer customer2 = new Customer("c1", "Jira", subscription2);


        CostExplorer costExplorer2 = new CostExplorer(customer2);


        BigDecimal[] bigDecimals2 = costExplorer2.monthlyCostList();

        System.out.print(" BASIC Plan :");
        System.out.println(Arrays.toString(bigDecimals2));


    }
}


