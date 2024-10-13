package Atlassian.PostKarat;

import java.util.HashMap;
import java.util.Map;

public class ContentPopularityTracker1 {


    Map<Integer, Integer> contentPopularityMap;
    int maxPopularity;
    int maxPopularContent;


    ContentPopularityTracker1() {

        this.contentPopularityMap = new HashMap<>();
        this.maxPopularity = Integer.MIN_VALUE;
        this.maxPopularContent = -1;
    }


    public void increasePopularity(int contentId) {

        int newPopularity = contentPopularityMap.getOrDefault(contentId, 0) + 1;

        contentPopularityMap.put(contentId, newPopularity);

        if (newPopularity > maxPopularity) {

            maxPopularity = newPopularity;
            maxPopularContent = contentId;
        } else if (newPopularity == maxPopularity) {

            maxPopularContent = contentId;
        }
    }


    public void decreasePopularity(int contentId) {

        int decreasedPopularity = contentPopularityMap.getOrDefault(contentId, 0) - 1;

        contentPopularityMap.put(contentId, decreasedPopularity);

        if (contentId == maxPopularContent && decreasedPopularity < maxPopularity) {

            updateMaxPopularContent();
        }
    }


    int getMostPopular() {
        return maxPopularContent;
    }

    private void updateMaxPopularContent() {


        // 7 -> 1   3
        // 8 -> 2
        // 9 -> 4 O(n) x O(1)

        this.maxPopularity = Integer.MIN_VALUE;
        this.maxPopularContent = -1;

        for (Map.Entry<Integer, Integer> entry : contentPopularityMap.entrySet()) {

            int contentId = entry.getKey();
            int popularity = entry.getValue();

            if (popularity > maxPopularity) {

                maxPopularity = popularity;
                maxPopularContent = contentId;
            }
        }
    }


    public static void main(String[] args) {


        ContentPopularityTracker1 contentPopularityTracker1 = new ContentPopularityTracker1();


        contentPopularityTracker1.increasePopularity(7);
        contentPopularityTracker1.increasePopularity(8);

        System.out.println(contentPopularityTracker1.getMostPopular());
    }
}
