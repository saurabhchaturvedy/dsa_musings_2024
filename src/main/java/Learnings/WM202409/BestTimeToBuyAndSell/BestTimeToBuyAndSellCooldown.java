package Learnings.WM202409.BestTimeToBuyAndSell;

public class BestTimeToBuyAndSellCooldown {

    public static int maxProfit(int[] prices) {


        if (prices == null || prices.length == 0) {
            return 0;
        }


        int buy = -prices[0];
        int sell = 0;
        int cooldown = 0;

        for (int i = 1; i < prices.length; i++) {

            int prevBuy = buy;

            buy = Math.max(buy, cooldown - prices[i]);
            cooldown = Math.max(sell, cooldown);
            sell = prevBuy + prices[i];
        }


        return Math.max(sell, cooldown);
    }


    public static void main(String[] args) {


        int[] prices = {1, 2, 3, 0, 2};

        System.out.println(" max profit is = " + maxProfit(prices));

    }
}
