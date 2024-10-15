package Atlassian.PostKaratRev1.FileCol2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReportService {


    public Report generateReport(List<FileMetadata> fileMetadataList, Map<String, Collection> collectionMap) {

        long totalSize = 0L;
        Map<String, Long> collectionSizes = new HashMap<>();


        for (FileMetadata fileMetadata : fileMetadataList) {

            totalSize += fileMetadata.getFileSize();

            for (String collectionId : fileMetadata.getCollectionIds()) {

                collectionSizes.put(collectionId, collectionSizes.getOrDefault(collectionId, 0L) + fileMetadata.getFileSize());
            }
        }


        for (Collection collection : collectionMap.values()) {

            long totalSizeOverall = calculateFileSize(collection, collectionSizes);
            collectionSizes.put(collection.getName(), totalSizeOverall);

        }


        return new Report(totalSize, collectionSizes);
    }


    public long calculateFileSize(Collection collection, Map<String, Long> collectionSizes) {

        long size = 0;

        for (String fileName : collection.getFiles()) {

            size += collectionSizes.getOrDefault(fileName, 0L);
        }


        for (Collection collection1 : collection.getCollections()) {

            size += calculateFileSize(collection1, collectionSizes);
        }

        return size;
    }
}
