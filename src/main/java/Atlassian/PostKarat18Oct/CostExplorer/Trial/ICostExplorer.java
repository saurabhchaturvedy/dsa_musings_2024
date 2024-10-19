package Atlassian.PostKarat18Oct.CostExplorer.Trial;

import java.math.BigDecimal;

public interface ICostExplorer {

    BigDecimal[] monthlyCostList();

    BigDecimal annualCost();
}
