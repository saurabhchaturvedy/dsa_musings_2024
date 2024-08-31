package Learnings.WM202409.BestTimeToBuyAndSell;

public class BestTimeToBuyAndSell {


    public int maxProfit(int[] prices) {

        if (prices.length == 0) {
            return 0;
        }


        int maxProfit = 0;
        int currentBuyingPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] - currentBuyingPrice > maxProfit) {

                maxProfit = prices[i] - currentBuyingPrice;
            } else {

                if (prices[i] < currentBuyingPrice) {

                    currentBuyingPrice = prices[i];
                }
            }
        }

        return maxProfit;
    }


    public static void main(String[] args) {


        int[] prices = {7, 1, 5, 3, 6, 4};

        BestTimeToBuyAndSell bestTimeToBuyAndSell = new BestTimeToBuyAndSell();

        int maxProfit = bestTimeToBuyAndSell.maxProfit(prices);

        System.out.println(" max profit is : " + maxProfit);
    }
}
