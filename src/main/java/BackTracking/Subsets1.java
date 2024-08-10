package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets1 {


    List<List<Integer>> result = new ArrayList<>();
    int[] arr;


    public List<List<Integer>> subsets(int[] arr) {

        this.arr = arr;
        dfs(new LinkedList<>(), 0);
        return result;
    }

    private void dfs(LinkedList<Integer> currComb, int index) {


        result.add(new ArrayList<>(currComb));

        for (int i = index; i < arr.length; i++) {

            currComb.add(arr[i]);
            dfs(currComb, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 3};

        Subsets1 subsets1 = new Subsets1();

        List<List<Integer>> subsets = subsets1.subsets(arr);

        System.out.println(subsets);
    }
}
