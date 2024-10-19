package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class DateTest {


    public static void main(String[] args) {


        LocalDate startDate = LocalDate.parse("2021-01-01");
        LocalDate endOfYear = LocalDate.of(startDate.getYear(), Month.DECEMBER, 31);

        Period between = Period.between(startDate, endOfYear);

        long totalMonths = between.toTotalMonths() + 1;

        BigDecimal[] monthlyCosts = new BigDecimal[12];

        BigDecimal monthlyCost = new BigDecimal(9.99);

        System.out.println(" Total months = " + totalMonths);


        for (int i = 0; i < totalMonths; i++) {

            int monthValue = startDate.getMonthValue() - 1;
            System.out.println("i= " + i + " month value = " + monthValue);
            monthlyCosts[monthValue] = monthlyCost;
            startDate = startDate.plusMonths(1);
        }
    }
}
