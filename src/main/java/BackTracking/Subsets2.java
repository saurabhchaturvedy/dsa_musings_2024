package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsets2 {


    List<List<Integer>> result = new ArrayList<>();
    int[] arr;


    public List<List<Integer>> subsets(int[] arr) {

        this.arr = arr;
        Arrays.sort(this.arr);
        dfs(new LinkedList<>(), 0);
        return result;
    }

    private void dfs(LinkedList<Integer> currComb, int index) {


        result.add(new ArrayList<>(currComb));

        for (int i = index; i < arr.length; i++) {

            if (i != index && arr[i] == arr[i - 1]) continue;

            currComb.add(arr[i]);
            dfs(currComb, i + 1);
            currComb.remove(currComb.size() - 1);
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 2, 2};

        Subsets2 subsets2 = new Subsets2();

        List<List<Integer>> subsets = subsets2.subsets(arr);

        System.out.println(subsets);
    }
}
