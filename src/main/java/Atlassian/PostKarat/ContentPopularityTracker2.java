package Atlassian.PostKarat;

import java.util.*;

public class ContentPopularityTracker2 {


    Map<Integer, Integer> contentPopularityMap;
    Map<Integer, Set<Integer>> popularityToContentIdsMap;

    int maxPopularity;


    ContentPopularityTracker2() {
        this.contentPopularityMap = new HashMap<>();
        this.popularityToContentIdsMap = new HashMap<>();
        this.maxPopularity = 0;
    }


    public void increasePopularity(int contentId) {

        int currentPopularity = contentPopularityMap.getOrDefault(contentId, 0);


        contentPopularityMap.put(contentId, currentPopularity + 1);

        if (currentPopularity > 0) {

            popularityToContentIdsMap.get(currentPopularity).remove(contentId);

            if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {
                popularityToContentIdsMap.remove(currentPopularity);
            }
        }


        if (!popularityToContentIdsMap.containsKey(currentPopularity + 1)) {

            popularityToContentIdsMap.put(currentPopularity + 1, new HashSet<>());
        }

        popularityToContentIdsMap.get(currentPopularity + 1).add(contentId);

        maxPopularity = Math.max(currentPopularity + 1, maxPopularity);
    }


    public void decreasePopularity(int contentId) {

        int currentPopularity = contentPopularityMap.getOrDefault(contentId, 0);

        if (currentPopularity == 0) return;


        contentPopularityMap.put(contentId, currentPopularity - 1);


        popularityToContentIdsMap.get(currentPopularity).remove(contentId);

        if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {

            popularityToContentIdsMap.remove(currentPopularity);


            maxPopularity = currentPopularity - 1;

        }


        if (currentPopularity - 1 > 0) {

            if (!popularityToContentIdsMap.containsKey(currentPopularity - 1)) {

                popularityToContentIdsMap.put(currentPopularity - 1, new HashSet<>());
            }

            popularityToContentIdsMap.get(currentPopularity - 1).add(contentId);

        }


    }


    public int mostPopular() {

        if (popularityToContentIdsMap.isEmpty()) {

            return -1;
        }


        return popularityToContentIdsMap.get(maxPopularity).iterator().next();
    }


    public static void main(String[] args) {


        ContentPopularityTracker2 contentPopularityTracker2 = new ContentPopularityTracker2();


        contentPopularityTracker2.increasePopularity(7);
        contentPopularityTracker2.increasePopularity(7);
        contentPopularityTracker2.increasePopularity(8);
        System.out.println(contentPopularityTracker2.mostPopular());

        contentPopularityTracker2.increasePopularity(8);
        contentPopularityTracker2.increasePopularity(8);
        System.out.println(contentPopularityTracker2.mostPopular());

        contentPopularityTracker2.decreasePopularity(8);
        contentPopularityTracker2.decreasePopularity(8);
        System.out.println(contentPopularityTracker2.mostPopular());

        contentPopularityTracker2.decreasePopularity(7);
        contentPopularityTracker2.decreasePopularity(7);
        contentPopularityTracker2.decreasePopularity(8);
        System.out.println(contentPopularityTracker2.mostPopular());


    }

}
