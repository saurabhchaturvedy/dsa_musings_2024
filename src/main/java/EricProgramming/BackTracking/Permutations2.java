package EricProgramming.BackTracking;

import java.util.*;

public class Permutations2 {


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permutations(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr) {

            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        dfs(new LinkedList<>(), map);

        return result;
    }

    private void dfs(List<Integer> currComb, Map<Integer, Integer> map) {


        if (map.isEmpty()) {

            List<Integer> permutation = new ArrayList<>(currComb);
            result.add(permutation);
            return;
        }


        Set<Integer> set = new HashSet<>(map.keySet());

        for (Integer x : set) {

            if (map.get(x) == 1) {

                map.remove(x);
            } else {

                map.put(x, map.get(x) - 1);
            }

            currComb.add(x);
            dfs(currComb, map);
            currComb.remove(currComb.size() - 1);
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
    }


    public static void main(String[] args) {


        int[] arr = {1, 1, 2};

        Permutations2 permutations2 = new Permutations2();

        List<List<Integer>> permutations = permutations2.permutations(arr);

        System.out.println(permutations);
    }
}
