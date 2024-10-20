package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel2;

import java.util.*;

class FileStorageSystem {
    private List<FileMetadata> files;
    private Map<String, CollectionMetadata> collections;

    public FileStorageSystem() {
        this.files = new ArrayList<>();
        this.collections = new HashMap<>();
    }

    public void addFile(FileMetadata file) {
        files.add(file); // Add file without any collection
    }

    public void addFileToCollection(String collectionId, FileMetadata file) {
        collections.computeIfAbsent(collectionId, id -> new CollectionMetadata(id)).addFile(file);
        // Optionally, if you want to track files in collections, add them to the main list as well
        files.add(file);
    }

    public void addChildCollection(String parentId, CollectionMetadata childCollection) {
        collections.computeIfAbsent(parentId, id -> new CollectionMetadata(id)).addChildCollection(childCollection);
    }

    public Report generateReport(int topNCollections) {
        long totalSize = 0;
        Map<String, Long> collectionSizes = new HashMap<>();

        // Calculate sizes of collections
        for (Map.Entry<String, CollectionMetadata> entry : collections.entrySet()) {
            long collectionSize = entry.getValue().calculateTotalSize();
            collectionSizes.put(entry.getKey(), collectionSize);
            totalSize += collectionSize;
        }

        // Calculate size of untagged files
        long untaggedFilesSize = files.stream()
                                       .filter(file -> !isFileInAnyCollection(file))
                                       .mapToLong(FileMetadata::getSize)
                                       .sum();

        totalSize += untaggedFilesSize; // Add untagged files to the total size

        return createTopCollectionsReport(totalSize, collectionSizes, topNCollections, untaggedFilesSize);
    }

    private boolean isFileInAnyCollection(FileMetadata file) {
        for (CollectionMetadata collection : collections.values()) {
            if (collection.getFiles().contains(file)) {
                return true;
            }
        }
        return false;
    }

    private Report createTopCollectionsReport(long totalSize, Map<String, Long> collectionSizes, int topN, long untaggedFilesSize) {
        List<Map.Entry<String, Long>> sortedCollections = new ArrayList<>(collectionSizes.entrySet());
        sortedCollections.sort((a, b) -> Long.compare(b.getValue(), a.getValue())); // Descending order

        Map<String, Long> topCollections = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(topN, sortedCollections.size()); i++) {
            Map.Entry<String, Long> entry = sortedCollections.get(i);
            topCollections.put(entry.getKey(), entry.getValue());
        }

        return new Report(totalSize, topCollections, untaggedFilesSize);
    }
}
