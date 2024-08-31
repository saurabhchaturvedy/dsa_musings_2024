package Learnings.WM202409.BestTimeToBuyAndSell;

public class BestTimeToBuyAndSell2 {


    public static int bestTimeToBuyAndSell2(int[] prices) {


        int maxProfit = 0;


        for (int i = 1; i < prices.length; i++) {

            if (prices[i] > prices[i - 1]) {
                maxProfit = maxProfit + prices[i] - prices[i-1];
            }
        }

        return maxProfit;
    }


    public static void main(String[] args) {


        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println(" max profit = " + bestTimeToBuyAndSell2(prices));
    }
}
