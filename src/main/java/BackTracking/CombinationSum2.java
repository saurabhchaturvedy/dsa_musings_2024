package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {


    List<List<Integer>> result = new ArrayList<>();

    int[] candidates;


    public List<List<Integer>> combinationSum(int[] arr, int target) {


        this.candidates = arr;
        Arrays.sort(candidates);

        List<Integer> currComb = new ArrayList<>();
        dfs(currComb, target, 0);
        return result;

    }

    private void dfs(List<Integer> currComb, int target, int index) {


        if (target == 0) {

            List<Integer> combination = new ArrayList<>(currComb);
            result.add(combination);
            return;
        }


        for (int i = index; i < candidates.length; i++) {

            if (i != index && candidates[i] == candidates[i - 1]) continue;

            int currSum = target - candidates[i];

            if (currSum < 0) break;

            currComb.add(candidates[i]);
            dfs(currComb, currSum, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }


    public static void main(String[] args) {


        int[] arr = {10, 1, 2, 7, 6, 1, 5};

        int target = 8;

        CombinationSum2 combinationSum2 = new CombinationSum2();

        List<List<Integer>> list = combinationSum2.combinationSum(arr, target);

        System.out.println(list);
    }
}
