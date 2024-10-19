package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;

public interface ICostExplorer {
    BigDecimal[] monthlyCostList();
    BigDecimal annualCost();
}