package Atlassian.PostKaratRev1.FileCollection;

import java.util.*;

public class FileCollectionService {


    public static Report generateReport(List<FileMetadata> fileMetadataList, int noOfCollections) {

        long fileSize = 0;
        Map<String, Long> collectionSizes = new HashMap<>();

        for (FileMetadata fileMetadata : fileMetadataList) {

            fileSize += fileMetadata.fileSize;

            if (fileMetadata.collectionId != null) {

                collectionSizes.put(fileMetadata.collectionId, collectionSizes.getOrDefault(fileMetadata, 0L) + fileMetadata.fileSize);
            }
        }


        List<Map.Entry<String, Long>> sortedList = new ArrayList<>(collectionSizes.entrySet());
        sortedList.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));


        Map<String, Long> topNCollections = new HashMap<>();

        for (int i = 0; i < Math.min(noOfCollections, sortedList.size()); i++) {

            Map.Entry<String, Long> entry = sortedList.get(i);
            topNCollections.put(entry.getKey(), entry.getValue());

        }


        return new Report(fileSize, topNCollections);

    }


    public static void main(String[] args) {


        List<FileMetadata> files = Arrays.asList(
                new FileMetadata("file1.txt", 100, "collection1"),
                new FileMetadata("file2.txt", 200, "collection1"),
                new FileMetadata("file3.txt", 300, "collection2"),
                new FileMetadata("file4.txt", 10, null)
        );


        Report report = generateReport(files, 2);


        for (Map.Entry<String, Long> entry : report.collectionToFileSizeMap.entrySet()) {


            System.out.println(" Total File Size : " + report.totalFileSize);
            System.out.println(" Collection ID : " + entry.getKey());
            System.out.println(" File Size : " + entry.getValue());
        }
    }
}
