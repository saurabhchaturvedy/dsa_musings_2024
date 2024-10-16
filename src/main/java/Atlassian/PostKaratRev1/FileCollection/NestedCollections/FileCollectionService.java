package Atlassian.PostKaratRev1.FileCollection.NestedCollections;

import java.util.*;

public class FileCollectionService {


    public static Report generateReport(List<Collection> collections) {

        long totalFileSize = 0;

        Map<String, Long> currentSizes = new HashMap<>();


        for (Collection collection : collections) {

            totalFileSize += calculateFileSize(collection, currentSizes);
        }


        return new Report(totalFileSize, currentSizes);
    }

    private static long calculateFileSize(Collection collection, Map<String, Long> currentSizes) {

        long totalFileSize = 0;


        for (FileMetadata fileMetadata : collection.files) {

            totalFileSize += fileMetadata.fileSize;
        }

        for (Collection coll : collection.collections) {

            totalFileSize += calculateFileSize(coll, currentSizes);
        }

        currentSizes.put(collection.collectionId, totalFileSize);
        return totalFileSize;
    }


    public static void main(String[] args) {


        Collection collection1 = new Collection("collection1");
        collection1.addFile(new FileMetadata("file1.txt", 100, new HashSet<>()));
        collection1.addFile(new FileMetadata("file2.txt", 200, new HashSet<>()));

        Collection collection2 = new Collection("collection2");
        collection2.addFile(new FileMetadata("file3.txt", 300, new HashSet<>()));


        Collection collection3 = new Collection("collection3");
        collection3.addCollection(collection1);
        collection3.addCollection(collection2);
        collection3.addFile(new FileMetadata("file4.txt", 400, new HashSet<>()));

        List<Collection> collections = Arrays.asList(collection1, collection2, collection3);

        Report report = generateReport(collections);
        System.out.println("Total File Size: " + report.totalFileSize);
        System.out.println("Collection Sizes:");
        for (Map.Entry<String, Long> entry : report.collectionToFileSizeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
