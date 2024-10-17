package Atlassian.PostKaratRev1.ContentPopularity.Constant;

import java.util.HashMap;
import java.util.Map;

public class PopularityContent {


    Map<Integer, Integer> contentToPopularityMap;
    Map<Integer, Node> contentToNodeMap;
    DoublyLinkedList recencyList;
    Node mostPopularContent;


    PopularityContent() {

        this.contentToPopularityMap = new HashMap<>();
        this.contentToNodeMap = new HashMap<>();
        this.recencyList = new DoublyLinkedList();
        this.mostPopularContent = null;
    }


    public void increasePopularity(int contentId) {


        contentToPopularityMap.put(contentId, contentToPopularityMap.getOrDefault(contentId, 0) + 1);

        if (!contentToNodeMap.containsKey(contentId)) {

            Node node = recencyList.addToEnd(contentId);
            contentToNodeMap.put(contentId, node);
        } else {

            recencyList.moveToEnd(contentToNodeMap.get(contentId));
        }


        updateMostPopularContent(contentId);
    }


    public void decreasePopularity(int contentId) {

        if (!contentToPopularityMap.containsKey(contentId)) return;


        contentToPopularityMap.put(contentId, contentToPopularityMap.getOrDefault(contentId, 0) - 1);

        if (contentToPopularityMap.get(contentId) <= 0) {

            contentToPopularityMap.remove(contentId);
            contentToNodeMap.remove(contentId);
            recencyList.remove(contentToNodeMap.get(contentId));
        }


        updateMostPopularContent(contentId);

    }


    public int getMostPopularContent() {


        return (mostPopularContent == null) ? -1 : mostPopularContent.contentId;
    }


    public void updateMostPopularContent(int contentId) {

        if (mostPopularContent == null || contentToPopularityMap.getOrDefault(contentId, 0) > contentToPopularityMap.get(mostPopularContent.contentId)) {

            mostPopularContent = contentToNodeMap.get(contentId);
        } else if (contentToPopularityMap.get(contentId).equals(contentToPopularityMap.get(mostPopularContent.contentId))) {

            mostPopularContent = recencyList.tail;
        }
    }

}
