package Atlassian.PostKarat;

import java.util.*;

public class ContentPopularityTracker3 {


    Map<Integer, Integer> contentPopularityMap;
    Map<Integer, LinkedHashSet<Integer>> popularityToContentIdsMap;

    //  8 -> 3
    //  3 -> [8,9]
    int maxPopularity;


    ContentPopularityTracker3() {
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

            popularityToContentIdsMap.put(currentPopularity + 1, new LinkedHashSet<>());
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

                popularityToContentIdsMap.put(currentPopularity - 1, new LinkedHashSet<>());
            }

            popularityToContentIdsMap.get(currentPopularity - 1).add(contentId);

        }


    }


    public int mostPopular() {

        if (popularityToContentIdsMap.isEmpty()) {

            return -1;
        }


        return popularityToContentIdsMap.get(maxPopularity).getLast();
    }


    public List<Integer> topK(int k) {


        List<Integer> result = new ArrayList<>();

        for (int i = maxPopularity; i > 0 && result.size() < k; i--) {

            LinkedHashSet<Integer> contentIds = popularityToContentIdsMap.getOrDefault(i, new LinkedHashSet<>());


           // Collections.reverse(new ArrayList<>(contentIds));
            for (Integer contentId : contentIds) {

                result.add(contentId);
                if (result.size() == k) {
                    break;
                }
            }
        }

        //Collections.reverse(result);
        return result;
    }


    public static void main(String[] args) {


        ContentPopularityTracker3 contentPopularityTracker3 = new ContentPopularityTracker3();


        System.out.println(contentPopularityTracker3.mostPopular());

        contentPopularityTracker3.increasePopularity(7);
        contentPopularityTracker3.increasePopularity(7);
        contentPopularityTracker3.increasePopularity(8);
        contentPopularityTracker3.increasePopularity(8);
        contentPopularityTracker3.increasePopularity(8);
//        contentPopularityTracker3.increasePopularity(9);
//        contentPopularityTracker3.increasePopularity(9);
//        contentPopularityTracker3.increasePopularity(9);
//        contentPopularityTracker3.increasePopularity(9);

        System.out.println(contentPopularityTracker3.mostPopular());


        System.out.println(" Top K :");
        System.out.println(contentPopularityTracker3.topK(2));


    }
}
