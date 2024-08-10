package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum3 {


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinations(int k, int n) {

        dfs(k, n, new LinkedList<>(), 1);
        return result;
    }

    private void dfs(int len, int sum, LinkedList<Integer> currComb, int currElement) {


        if (len == 0 && sum == 0) {
            List<Integer> combination = new ArrayList<>(currComb);
            result.add(combination);
            return;
        }

        if (len == 0) return;

        for (int i = currElement; i <= 9; i++) {

            int currSum = sum - i;

            if (currSum < 0) break;

            currComb.add(i);
            dfs(len-1, currSum, currComb, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }


    public static void main(String[] args) {


        CombinationSum3 combinationSum3 = new CombinationSum3();

        List<List<Integer>> combinations = combinationSum3.combinations(3, 9);

        System.out.println(combinations);
    }
}
