package EricProgramming.Intervals;

import java.util.Arrays;

public class MinimumNumberOfPlatforms {


    public static int minimumNumberOfPlatforms(int[] arr, int[] dep) {

        if (arr.length == 0 || dep.length == 0) {
            return -1;
        }


        Arrays.sort(arr);
        Arrays.sort(dep);

        int platformsNeeded = 0;
        int minimumPlatforms = 0;


        int i = 0;
        int j = 0;

        while (i < arr.length) {

            if (arr[i] < dep[j]) {

                platformsNeeded++;
                i++;
            } else {

                platformsNeeded--;
                j++;
            }


            minimumPlatforms = Math.max(platformsNeeded, minimumPlatforms);
        }


        return minimumPlatforms;
    }


    public static void main(String[] args) {


        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};


        System.out.println(" minimum platforms needed : " + minimumNumberOfPlatforms(arr, dep));
    }
}
