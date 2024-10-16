package Atlassian.PostKaratRev1.FileCollection1;

import java.util.*;

public class FileCollectionService {


    public static Report generateReport(List<FileMetadata> fileMetadataList, int noOfCollectionsToReport) {


        long fileSize = 0;


        Map<String, Long> collectionSizes = new HashMap<>();


        for (FileMetadata fileMetadata : fileMetadataList) {

            fileSize += fileMetadata.fileSize;

            if (fileMetadata.collectionId != null) {

                collectionSizes.put(fileMetadata.collectionId, collectionSizes.getOrDefault(fileMetadata.collectionId, 0L) + fileMetadata.fileSize);
            }
        }


        List<Map.Entry<String, Long>> sortedEntries = new ArrayList<>(collectionSizes.entrySet());

        sortedEntries.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));


        Map<String, Long> topNCollections = new LinkedHashMap<>();

        for (int i = 0; i < Math.min(noOfCollectionsToReport, sortedEntries.size()); i++) {

            Map.Entry<String, Long> entry = sortedEntries.get(i);

            topNCollections.put(entry.getKey(), entry.getValue());
        }


        return new Report(fileSize, topNCollections);
    }


    public static void main(String[] args) {


        List<FileMetadata> files = Arrays.asList(
                new FileMetadata("file1.txt", 100, "collection1"),
                new FileMetadata("file2.txt", 200, "collection1"),
                new FileMetadata("file3.txt", 300, "collection2"),
                new FileMetadata("file4.txt", 10, "collection1"),
                new FileMetadata("file5.txt", 150, "collection3"),
                new FileMetadata("file6.txt", 80, "collection1"),
                new FileMetadata("file7.txt", 450, "collection2")
        );


        Report report = generateReport(files, 2);


        System.out.println(" Total File Size : " + report.totalFileSize);

        for (Map.Entry<String, Long> entry : report.collectionSizes.entrySet()) {

            System.out.println(" Collection ID : " + entry.getKey() + " File Size : " + entry.getValue());
        }
    }
}
