package Atlassian.PostKaratRev1.ContentPopularity.Constant;

import java.util.List;

public class Main {


    public static void main(String[] args) {


        PopularityConstant tracker = new PopularityConstant();

        // Increase popularity for content IDs 7 and 8
        tracker.increasePopularity(7);
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular());  // returns 8 (most recent)

        // Increase popularity of content ID 8 twice and 7 once
        tracker.increasePopularity(8);
        tracker.increasePopularity(7);
        System.out.println(tracker.mostPopular());  // returns 7 (tie but most recent)

        // Increase popularity of content ID 8
        tracker.increasePopularity(8);
        System.out.println(tracker.mostPopular());  // returns 8 (popularity of 8 is now higher)

        // Decrease popularity of content ID 8
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular());  // returns 7 (now both have the same popularity, return most recent)

        // Decrease popularity of content ID 7
        tracker.decreasePopularity(7);
        System.out.println(tracker.mostPopular());  // returns 8 (popularity of 7 decreased)

        // Decrease popularity of content ID 8 twice
        tracker.decreasePopularity(8);
        tracker.decreasePopularity(8);
        System.out.println(tracker.mostPopular());

        tracker.increasePopularity(9);
        tracker.increasePopularity(9);
        tracker.increasePopularity(9);
        tracker.increasePopularity(9);

        List<Integer> list = tracker.topKPopular(2);


        System.out.println("top k popular");
        for (int x : list) {
            System.out.print(x + " ");
        }
        System.out.println(tracker.mostPopular());

        Integer count = tracker.numberOfContentsWithAtleastPopularity(2);

        System.out.println(" Count of content with atleast popularity : 2 "+count);
    }



}
