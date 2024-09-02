package CodingSimplified.DynamicProgramming.DP001;

public class MinCostClimbingStairs {


    public static int minCostClimbingStairs(int[] cost) {

        return Math.min(minCostClimbingStairs(cost, 0), minCostClimbingStairs(cost, 1));
    }

    private static int minCostClimbingStairs(int[] cost, int i) {


        if (i >= cost.length) {
            return 0;
        }


        int move_one = cost[i] + minCostClimbingStairs(cost, i + 1);
        int move_two = cost[i] + minCostClimbingStairs(cost, i + 2);

        return Math.min(move_one, move_two);
    }


    public static void main(String[] args) {


        int[] arr = {10, 15, 20};


        System.out.println(" min cost climbing stairs = " + minCostClimbingStairs(arr));
    }
}
