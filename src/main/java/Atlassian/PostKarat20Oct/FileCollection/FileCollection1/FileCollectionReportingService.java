package Atlassian.PostKarat20Oct.FileCollection.FileCollection1;

import java.util.*;

public class FileCollectionReportingService {


    public long getTotalFileSize(List<FileMetadata> files) {

        long totalSize = 0;

        for (FileMetadata file : files) {

            totalSize = totalSize + file.getSize();

        }

        return totalSize;
    }


    public Map<String, Long> getCollectionSizes(List<FileMetadata> files) {

        int totalSize = 0;

        Map<String, Long> collectionFileSizeMap = new HashMap<>();

        for (FileMetadata file : files) {

            List<String> collections = file.getCollections();
            if (collections != null) {
                for (String collection : collections) {

                    if (collection != null) {
                        collectionFileSizeMap.put(collection, collectionFileSizeMap.getOrDefault(collection, 0L) + file.getSize());
                    }
                }
            }
        }

        return collectionFileSizeMap;
    }


    public Report generateReport(List<FileMetadata> files, int topK) {

        long totalFileSize = getTotalFileSize(files);
        Map<String, Long> collectionFileSizes = getCollectionSizes(files);
        List<Map.Entry<String, Long>> topKCollections = getTopKCollections(collectionFileSizes, topK);

        return new Report(topKCollections, totalFileSize);
    }


    public List<Map.Entry<String, Long>> getTopKCollections(Map<String, Long> collectionFileSizes, int topK) {


        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(collectionFileSizes.entrySet());
        //  sortedEntries.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));
        //  sortedEntries.subList(0, Math.max(topK, sortedEntries.size()));


        // O(F + MlogN) Time Complexity
        PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

        for (Map.Entry<String, Long> entry : collectionFileSizes.entrySet()) {

            minHeap.add(entry);

            if (minHeap.size() > topK) {
                minHeap.poll();
            }


        }

        List<Map.Entry<String, Long>> topKCollections = new ArrayList<>();

        while (!minHeap.isEmpty()) {

            topKCollections.add(minHeap.poll());
        }

        System.out.println(topKCollections);


        Collections.reverse(topKCollections);
        return topKCollections;

    }
}
