package Atlassian.PostKaratRev1.FileCollection.MultipleCollections;

import java.util.*;

public class FileCollectionService {


    public static Report generateReport(List<FileMetadata> fileMetadataList, int noOfCollections) {

        long totalFileSize = 0;
        Map<String, Long> collectionSizes = new HashMap<>();

        for (FileMetadata fileMetadata : fileMetadataList) {

            totalFileSize += fileMetadata.fileSize;

            for (String collectionId : fileMetadata.collectionIds) {

                collectionSizes.put(collectionId, collectionSizes.getOrDefault(collectionId, 0L) + fileMetadata.fileSize);
            }
        }


        PriorityQueue<Map.Entry<String, Long>> minHeap = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (Map.Entry<String, Long> entry : collectionSizes.entrySet()) {

            minHeap.add(entry);

            if (minHeap.size() > 2) {

                minHeap.poll();
            }

        }
        Map<String, Long> topNCollections = new LinkedHashMap<>();

        while (!minHeap.isEmpty()) {
            Map.Entry<String, Long> ent = minHeap.poll();
            topNCollections.put(ent.getKey(), ent.getValue());
        }


        return new Report(totalFileSize, topNCollections);
    }


    public static void main(String[] args) {


        List<FileMetadata> files = Arrays.asList(
                new FileMetadata("file1.txt", 100, new HashSet<>(Arrays.asList("collection1"))),
                new FileMetadata("file2.txt", 200, new HashSet<>(Arrays.asList("collection1"))),
                new FileMetadata("file3.txt", 300, new HashSet<>(Arrays.asList("collection2"))),
                new FileMetadata("file4.txt", 400, new HashSet<>(Arrays.asList("collection1", "collection2"))), // Belongs to 2 collections
                new FileMetadata("file5.txt", 10, new HashSet<>()) // No collection
        );

        Report report = generateReport(files, 2);
        System.out.println("Total File Size: " + report.totalFileSize);
        System.out.println("Top Collections:");
        for (Map.Entry<String, Long> entry : report.collectionToFileSizeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
