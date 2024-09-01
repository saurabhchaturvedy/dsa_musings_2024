package EricProgramming.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum1 {


    List<List<Integer>> result = new ArrayList<>();
    int[] candidates;


    public List<List<Integer>> combinationSum(int[] arr, int target) {


        this.candidates = arr;
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

            int currSum = target - candidates[i];

            if (currSum < 0) continue;

            currComb.add(candidates[i]);
            dfs(currComb, currSum, i);
            currComb.remove(currComb.size() - 1);
        }
    }


    public static void main(String[] args) {


        int[] arr = {2, 3, 6, 7};

        int target = 7;

        CombinationSum1 combinationSum1 = new CombinationSum1();

        List<List<Integer>> list = combinationSum1.combinationSum(arr, target);

        System.out.println(list);
    }
}
