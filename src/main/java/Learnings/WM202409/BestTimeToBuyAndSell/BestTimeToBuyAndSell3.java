package Learnings.WM202409.BestTimeToBuyAndSell;

public class BestTimeToBuyAndSell3 {


    public static int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) {

            return 0;
        }

        int n = prices.length;
        int[] dpCurr = new int[n];
        int[] dpPrev = new int[n];

        for (int i = 1; i <= 2; i++) {

            int maxDiff = Integer.MIN_VALUE;

            for (int j = 0; j < n; j++) {

                if (j > 0) {
                    dpCurr[j] = Math.max(dpCurr[j - 1], prices[j] + maxDiff);
                }
                maxDiff = Math.max(maxDiff, (j > 0 ? dpPrev[j] : 0) - prices[j]);
            }

            System.arraycopy(dpCurr, 0, dpPrev, 0, n);
        }

        return dpCurr[n - 1];
    }

    public static void main(String[] args) {


        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

        System.out.println(" max profit =" + maxProfit(prices));
    }
}
