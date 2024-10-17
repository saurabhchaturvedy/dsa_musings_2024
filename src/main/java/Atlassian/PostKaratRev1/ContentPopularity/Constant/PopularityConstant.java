package Atlassian.PostKaratRev1.ContentPopularity.Constant;

import java.util.*;

public class PopularityConstant {


    Map<Integer, Integer> contentToPopularityMap;
    TreeMap<Integer, LinkedHashSet<Integer>> popularityToContentIdsMap;
    int mostRecentContentId;


    PopularityConstant() {

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
        mostRecentContentId = contentId;
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

        mostRecentContentId = contentId;
    }


    public int mostPopular() {

        if (popularityToContentIdsMap.isEmpty()) {
            return -1;
        }


        return popularityToContentIdsMap.lastEntry().getValue().iterator().next();
    }


    public List<Integer> topKPopular(int topK) {

        List<Integer> result = new ArrayList<>();


        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : popularityToContentIdsMap.descendingMap().entrySet()) {

            LinkedHashSet<Integer> list = entry.getValue();

            for (Integer contentId : list) {

                result.add(contentId);
                if (result.size() == topK) {
                    break;
                }
            }


        }

        return result;
    }
}
