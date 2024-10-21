package Atlassian.PostKarat20Oct.Popularity;

import java.util.*;

public class PopularityCreator {


    Map<Integer, Integer> contentToPopularityMap;
    TreeMap<Integer, LinkedHashSet<Integer>> popularityToContentIdsMap;
    int mostRecentContentId;


    PopularityCreator() {

        this.contentToPopularityMap = new HashMap<>();
        this.popularityToContentIdsMap = new TreeMap<>();
        this.mostRecentContentId = -1;
    }


    public void increasePopularity(int contentId) {

        int currentPopularity = contentToPopularityMap.getOrDefault(contentId, 0);


        if (currentPopularity > 0) {

            popularityToContentIdsMap.get(currentPopularity).remove(contentId);
            if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {

                popularityToContentIdsMap.remove(currentPopularity);
            }
        }

        int newPopularity = currentPopularity + 1;
        contentToPopularityMap.put(contentId, newPopularity);
        popularityToContentIdsMap.computeIfAbsent(newPopularity, k -> new LinkedHashSet<>()).add(contentId);

        this.mostRecentContentId = contentId;
    }


    public void decreasePopularity(int contentId) {

        if (!contentToPopularityMap.containsKey(contentId)) {

            return;
        }

        int currentPopularity = contentToPopularityMap.getOrDefault(contentId, 0);

        popularityToContentIdsMap.get(currentPopularity).remove(contentId);
        if (popularityToContentIdsMap.get(currentPopularity).isEmpty()) {

            popularityToContentIdsMap.remove(currentPopularity);
        }


        int newPopularity = currentPopularity - 1;

        if (newPopularity > 0) {

            contentToPopularityMap.put(contentId, newPopularity);
            popularityToContentIdsMap.computeIfAbsent(newPopularity, k -> new LinkedHashSet<>()).add(contentId);
        } else {

            contentToPopularityMap.remove(contentId);
        }


        this.mostRecentContentId = contentId;
    }


    public int mostPopular() {

        if (popularityToContentIdsMap.isEmpty()) {
            return -1;
        }

        return popularityToContentIdsMap.lastEntry().getValue().getLast();
    }


    public List<Integer> getTopK(int topK) {

        List<Integer> result = new ArrayList<>();


        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : this.popularityToContentIdsMap.descendingMap().entrySet()) {

            LinkedHashSet<Integer> contentIds = entry.getValue();

            for (Integer contentId : contentIds) {

                result.add(contentId);

                if (result.size() == topK) {
                    break;
                }
            }


        }

        return result;
    }


    public Integer numberOfContentWithAtleastPopularity(int k) {


        int numberOfContentIds = 0;


        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : this.popularityToContentIdsMap.descendingMap().entrySet()) {

            int popularity = entry.getKey();

            if (popularity >= k) {
                int contentIdsSize = entry.getValue().size();
                numberOfContentIds += contentIdsSize;

            }
        }

        return numberOfContentIds;
    }


    public static void main(String[] args) {


        PopularityCreator popularityCreator = new PopularityCreator();


        popularityCreator.increasePopularity(7);
        popularityCreator.increasePopularity(7);
        popularityCreator.increasePopularity(8);
        popularityCreator.increasePopularity(8);

        System.out.println(popularityCreator.mostPopular());


        popularityCreator.decreasePopularity(8);

        System.out.println(popularityCreator.mostPopular());

        popularityCreator.increasePopularity(8);
        popularityCreator.increasePopularity(8);
        popularityCreator.increasePopularity(8);

        popularityCreator.increasePopularity(4);
        popularityCreator.increasePopularity(4);
        popularityCreator.increasePopularity(4);
        popularityCreator.increasePopularity(4);

        System.out.println(popularityCreator.mostPopular());

        System.out.println(popularityCreator.getTopK(2));

        System.out.println(popularityCreator.numberOfContentWithAtleastPopularity(2));
    }
}
