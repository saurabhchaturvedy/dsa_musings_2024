package Atlassian.KaratFriday;

import java.util.*;

public class ParentChildren {


    public static List<List<Integer>> parentChildren(int[][] parentChildren) {

        List<Integer> zeroParentSet = new ArrayList<>();
        List<Integer> exactlyParentSet = new ArrayList<>();
        Map<Integer, Integer> childParentCountMap = new HashMap<>();

        Set<Integer> allIndividuals = new HashSet<>();

        for (int[] pc : parentChildren) {

            int parent = pc[0];
            int child = pc[1];

            allIndividuals.add(parent);
            allIndividuals.add(child);
            childParentCountMap.put(child, childParentCountMap.getOrDefault(child, 0) + 1);
        }


        for (Integer x : allIndividuals) {


            int parentCount = childParentCountMap.getOrDefault(x, 0);

            if (parentCount == 0) {

                zeroParentSet.add(x);
            } else if (parentCount == 1) {

                exactlyParentSet.add(x);
            }
        }

        return Arrays.asList(zeroParentSet, exactlyParentSet);
    }


    public static void main(String[] args) {


        int[][] parentChildPairs = {
                {1, 3},
                {2, 3},
                {3, 6},
                {5, 6},
                {5, 7},
                {4, 5},
                {4, 8},
                {8, 10}
        };


        System.out.println(parentChildren(parentChildPairs));
    }

}
