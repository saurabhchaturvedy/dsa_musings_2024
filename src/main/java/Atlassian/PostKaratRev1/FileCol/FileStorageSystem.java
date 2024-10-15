package Atlassian.PostKaratRev1.FileCol;

import java.util.*;

public class FileStorageSystem {


    public Report generateReport(List<FileMetadata> fileMetadataList, int topNCollections) {


        long totalSize = 0L;
        Map<String, Long> collectionMap = new LinkedHashMap<>();

        for (FileMetadata fileMetadata : fileMetadataList) {

            totalSize = totalSize + fileMetadata.getFileSize();

            String collectionId = fileMetadata.getCollectionId();


            if (collectionId != null) {

                collectionMap.put(collectionId, collectionMap.getOrDefault(collectionId, 0L) + fileMetadata.getFileSize());

            }
        }


        List<Map.Entry<String, Long>> collectionsEntrySet = new ArrayList<>(collectionMap.entrySet());
        collectionsEntrySet.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

        Map<String, Long> topNColl = new LinkedHashMap<>();

        for (int i = 0; i < Math.min(topNCollections, collectionsEntrySet.size()); i++) {


            Map.Entry<String, Long> entry = collectionsEntrySet.get(i);

            topNColl.put(entry.getKey(), entry.getValue());
        }

        return new Report(totalSize, topNColl);
    }
}
