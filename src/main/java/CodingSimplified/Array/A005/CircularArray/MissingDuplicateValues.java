package CodingSimplified.Array.A005.CircularArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissingDuplicateValues {


    public static Map<String, List<Integer>> missingDuplicateValues(int[] arr) {

        Map<String, List<Integer>> missingDuplicateMap = new HashMap<>();

        if (arr.length == 0) {
            missingDuplicateMap.put("Missing", new ArrayList<>());
            missingDuplicateMap.put("Duplicates", new ArrayList<>());
            return missingDuplicateMap;

        }


        int i = 0;

        while (i < arr.length) {

            if (arr[i] != arr[arr[i] - 1]) {

                int otherIndex = arr[i] - 1;

                int temp = arr[otherIndex];
                arr[otherIndex] = arr[i];
                arr[i] = temp;
            } else {

                i++;
            }
        }


        List<Integer> missing = new ArrayList<>();
        List<Integer> duplicates = new ArrayList<>();


        for (i = 0; i < arr.length; i++) {

            if (arr[i] != i + 1) {

                missing.add(i + 1);
                duplicates.add(arr[i]);
            }
        }


        missingDuplicateMap.put("Missing", missing);
        missingDuplicateMap.put("Duplicates", duplicates);

        return missingDuplicateMap;
    }


    public static void main(String[] args) {


        int[] arr = {2, 6, 4, 4, 3, 2};

        Map<String, List<Integer>> stringListMap = missingDuplicateValues(arr);

        System.out.println(stringListMap);
    }
}
