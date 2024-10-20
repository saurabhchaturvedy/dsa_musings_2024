package Atlassian.PostKarat20Oct.FileCollection.FileCollectionHierarchical;

import java.util.*;

public class FileStorageSystem {


    List<FileMetadata> files;
    Map<String, CollectionMetadata> collections;


    FileStorageSystem() {

        this.files = new ArrayList<>();
        this.collections = new HashMap<>();
    }


    public void addFileToCollection(String collectionId, FileMetadata file) {

        collections.computeIfAbsent(collectionId, CollectionMetadata::new).addFile(file);
        this.files.add(file);
    }


    public void addChildCollection(String parentId, CollectionMetadata childCollection) {

        collections.computeIfAbsent(parentId, CollectionMetadata::new).addChildCollection(childCollection);
    }


    public Report generateReport(int topK) {

        long totalFileSize = 0;
        Map<String, Long> collectionNameToTotalFileSizeMap = new HashMap<>();

        for (Map.Entry<String, CollectionMetadata> entry : collections.entrySet()) {

            long collectionsFileSize = entry.getValue().totalFilesSizeInCollection();
            collectionNameToTotalFileSizeMap.put(entry.getKey(), collectionsFileSize);
            totalFileSize += collectionsFileSize;

        }


        Map<String, Long> topNCollections = getTopNCollections(collectionNameToTotalFileSizeMap, topK);

        return new Report(totalFileSize, topNCollections);
    }

    private Map<String, Long> getTopNCollections(Map<String, Long> collectionNameToTotalFileSizeMap, int topK) {


        List<Map.Entry<String, Long>> sortedList = new ArrayList<>(collectionNameToTotalFileSizeMap.entrySet());
        sortedList.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));


        Map<String, Long> topNCollections = new LinkedHashMap<>();

        for (int i = 0; i < Math.min(topK, sortedList.size()); i++) {

            Map.Entry<String, Long> entry = sortedList.get(i);
            topNCollections.put(entry.getKey(), entry.getValue());
        }

        return topNCollections;
    }
}
