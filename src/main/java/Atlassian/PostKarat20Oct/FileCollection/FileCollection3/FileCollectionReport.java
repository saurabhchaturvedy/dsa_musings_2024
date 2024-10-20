package Atlassian.PostKarat20Oct.FileCollection.FileCollection3;

import java.util.*;

public class FileCollectionReport {

    public static Report generateReport(List<CollectionMetadata> collections, int numberOfCollectionsToReport) {
        long totalSize = 0L;
        Map<String, Long> collectionSizeMap = new HashMap<>();

        // Calculate the total size of each collection (including child collections)
        for (CollectionMetadata collection : collections) {
            long collectionSize = collection.getTotalSize();
            collectionSizeMap.put(collection.collectionName, collectionSize);
            totalSize += collectionSize;
        }

        // Sort collections by size in descending order and keep the top N
        PriorityQueue<Map.Entry<String, Long>> topCollectionsQueue = new PriorityQueue<>(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        );
        topCollectionsQueue.addAll(collectionSizeMap.entrySet());

        // Get the top N collections
        List<Map.Entry<String, Long>> topCollections = new ArrayList<>();
        for (int i = 0; i < numberOfCollectionsToReport && !topCollectionsQueue.isEmpty(); i++) {
            topCollections.add(topCollectionsQueue.poll());
        }

        return new Report(totalSize, topCollections);
    }

    public static void main(String[] args) {
        // Create collections and files
        CollectionMetadata collection1 = new CollectionMetadata("collection1");
        CollectionMetadata collection2 = new CollectionMetadata("collection2");
        CollectionMetadata collection3 = new CollectionMetadata("collection3");
        
        collection1.addFile(new FileMetadata("file2.txt", 200));
        collection1.addFile(new FileMetadata("file3.txt", 200));

        collection2.addFile(new FileMetadata("file4.txt", 200));
        collection3.addFile(new FileMetadata("file4.txt", 300));
        
        // collection2 is a child of collection1
        collection1.addChildCollection(collection2);

        // Add collections to the list
        List<CollectionMetadata> collections = new ArrayList<>();
        collections.add(collection1);
        collections.add(collection3); // collection3 is standalone

        // Generate the report
        Report report = generateReport(collections, 2);
        report.printReport();
    }
}