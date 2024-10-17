package Atlassian.PostKaratRev1.ContentPopularity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PopularityON {


    Map<Integer, Integer> contentToPopularityMap;
    LinkedList<Integer> recencyList;
    int mostPopularContent;


    PopularityON() {

        this.contentToPopularityMap = new HashMap<>();
        this.recencyList = new LinkedList<>();
        this.mostPopularContent = -1;
    }


    public void increasePopularity(int contentId) {


        contentToPopularityMap.put(contentId, contentToPopularityMap.getOrDefault(contentId, 0) + 1);
        updateRecency(contentId);


        if (mostPopularContent == -1 || contentToPopularityMap.get(contentId) > contentToPopularityMap.get(mostPopularContent)) {

            mostPopularContent = contentId;
        } else if (contentToPopularityMap.get(contentId).equals(contentToPopularityMap.get(mostPopularContent))) {

            mostPopularContent = recencyList.getLast();
        }
    }


    public void decreasePopularity(int contentId) {

        if (contentToPopularityMap.containsKey(contentId)) {


            contentToPopularityMap.put(contentId, contentToPopularityMap.get(contentId) - 1);

            if (contentToPopularityMap.get(contentId) <= 0) {

                contentToPopularityMap.remove(contentId);
                recencyList.remove((Integer) contentId);
            }
        }

        updateMostPopular();
    }


    public int getMostPopularContent() {


        return mostPopularContent == -1 || contentToPopularityMap.getOrDefault(mostPopularContent, 0) <= 0 ? -1 : mostPopularContent;
    }


    public void updateRecency(int contentId) {

        recencyList.remove((Integer) contentId);
        recencyList.add(contentId);
    }


    public void updateMostPopular() {

        for (int contentId : recencyList) {

            if (mostPopularContent == -1 || contentToPopularityMap.getOrDefault(contentId, 0) > contentToPopularityMap.get(mostPopularContent)) {

                mostPopularContent = contentId;
            }
        }
    }


    public static void main(String[] args) {


        PopularityON popularityON = new PopularityON();

        popularityON.increasePopularity(7);
        popularityON.increasePopularity(7);
        popularityON.increasePopularity(8);
        popularityON.increasePopularity(8);

        System.out.println(popularityON.getMostPopularContent());
    }
}
