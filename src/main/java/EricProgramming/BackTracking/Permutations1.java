package EricProgramming.BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations1 {


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permutations(int[] arr) {

        List<Integer> list = new ArrayList<>();

        for (int x : arr) {

            list.add(x);
        }


        dfs(list, new LinkedList<>());
        return result;
    }

    private void dfs(List<Integer> list, LinkedList<Integer> currComb) {

        if (list.isEmpty()) {
            List<Integer> permutation = new ArrayList<>(currComb);
            result.add(permutation);
            return;

        }


        for (int i = 0; i < list.size(); i++) {

            Integer num = list.remove(i);
            currComb.add(num);
            dfs(list, currComb);
            currComb.remove(currComb.size() - 1);
            list.add(i, num);
        }
    }


    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        Permutations1 permutations1 = new Permutations1();

        List<List<Integer>> permutations = permutations1.permutations(arr);

        System.out.println(permutations);
    }
}
