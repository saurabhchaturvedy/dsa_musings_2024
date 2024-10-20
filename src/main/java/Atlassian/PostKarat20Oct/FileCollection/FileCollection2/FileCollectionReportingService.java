package Atlassian.PostKarat20Oct.FileCollection.FileCollection2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileCollectionReportingService {


    public Report generateReport(List<Collection> collectionList, int topK) {

        long totalFileSize = 0;

        Map<String, Long> map = new HashMap<>();


        for (Collection collection : collectionList) {

            long size = collection.getTotalFileSize();

            totalFileSize = totalFileSize + size;

            if (collection.collection != null) {
                map.put(collection.collection, map.getOrDefault(collection.collection, 0L) + size);

            }
        }


        List<Map.Entry<String, Long>> topKCollections = getTopKCollections(map, topK);


        return new Report(totalFileSize, topKCollections);

    }

    private List<Map.Entry<String, Long>> getTopKCollections(Map<String, Long> map, int topK) {

        List<Map.Entry<String, Long>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

        return sortedList.subList(0, Math.max(topK, sortedList.size()));
    }
}
