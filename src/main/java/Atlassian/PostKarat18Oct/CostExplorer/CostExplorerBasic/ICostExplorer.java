package Atlassian.PostKarat18Oct.CostExplorer.CostExplorerBasic;

import java.math.BigDecimal;

public interface ICostExplorer {


    BigDecimal[] monthlyCostList();

    BigDecimal annualCost();
}
