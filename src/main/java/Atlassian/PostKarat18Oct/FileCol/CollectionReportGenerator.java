package Atlassian.PostKarat18Oct.FileCol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CollectionReportGenerator {
    public long getTotalFileSize(List<FileMetadata> files) {
        long totalSize = 0;
        for (FileMetadata file : files) {
            totalSize += file.getSize();
        }
        return totalSize;
    }

    public Map<String, Long> getCollectionSizes(List<FileMetadata> files) {
        Map<String, Long> collectionSizes = new HashMap<>();
        for (FileMetadata file : files) {
            List<String> collections = file.getCollections();
            if (collections != null) {
                for (String collection : collections) {
                    collectionSizes.put(
                        collection, 
                        collectionSizes.getOrDefault(collection, 0L) + file.getSize()
                    );
                }
            }
        }
        return collectionSizes;
    }

    public List<Map.Entry<String, Long>> getTopNCollections(Map<String, Long> collectionSizes, int N) {
        List<Map.Entry<String, Long>> sortedCollections = new ArrayList<>(collectionSizes.entrySet());
        sortedCollections.sort((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()));
        return sortedCollections.subList(0, Math.min(N, sortedCollections.size()));
    }

    public void generateReport(List<FileMetadata> files, int N) {
        long totalSize = getTotalFileSize(files);
        Map<String, Long> collectionSizes = getCollectionSizes(files);
        List<Map.Entry<String, Long>> topCollections = getTopNCollections(collectionSizes, N);

        System.out.println("Total file size: " + totalSize);
        System.out.println("Top " + N + " collections by size:");
        for (Map.Entry<String, Long> entry : topCollections) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}