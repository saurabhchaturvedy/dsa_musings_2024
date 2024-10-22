package Atlassian.PostKarat20Oct.CostExplorer;

import java.math.BigDecimal;

public interface ICostExplorer {


    BigDecimal[] monthlyCostList();

    BigDecimal annualCost();
}
