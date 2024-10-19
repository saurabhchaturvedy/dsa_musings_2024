package Atlassian.PostKarat18Oct.CostExplorer;

import java.math.BigDecimal;

public interface CostExplorer {

    BigDecimal[] monthlyCostList();

    BigDecimal annualCost();
}
