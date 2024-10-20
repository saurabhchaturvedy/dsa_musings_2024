package Atlassian.PostKarat20Oct.FileCollection.FileCollectionUnTaggedFiles;

import java.util.*;

class FileStorageSystem {
    private List<FileMetadata> files;
    private Map<String, CollectionMetadata> collections;
    private PriorityQueue<Map.Entry<String, Long>> topCollections; // To maintain top collections

    public FileStorageSystem() {
        this.files = new ArrayList<>();
        this.collections = new HashMap<>();
        this.topCollections = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    }

    public void addFile(FileMetadata file) {
        files.add(file); // Add file without any collection
    }
    boolean isChild=false;
    public void addFileToCollection(String collectionId, FileMetadata file) {

        if(collections.containsKey(collectionId))
        {
            CollectionMetadata collectionMetadata = collections.get(collectionId);
            for(FileMetadata fileMetadata : collectionMetadata.getFiles())
            {

                if(fileMetadata.equals(file))
                {
                    this.isChild=true;
                    break;
                }
            }

        }
        collections.computeIfAbsent(collectionId, id -> new CollectionMetadata(id)).addFile(file);
        files.add(file);
        updateTopCollections(collectionId,isChild);
    }

    public void addChildCollection(String parentId, CollectionMetadata childCollection) {

        collections.computeIfAbsent(parentId, id -> new CollectionMetadata(id)).addChildCollection(childCollection);
        updateTopCollections(childCollection.getId(),this.isChild);
    }

    private void updateTopCollections(String collectionId,boolean isChild) {
        if(!isChild) {topCollections.removeIf(entry -> entry.getKey().equals(collectionId));}
        CollectionMetadata collection = collections.get(collectionId);
        if (collection != null) {
            long size = collection.getTotalSize();
            if(!isChild) {topCollections.offer(new AbstractMap.SimpleEntry<>(collectionId, size));}
        }
    }

    public Report generateReport(int topNCollections) {
        long totalSize = 0;
        Map<String, Long> collectionSizes = new HashMap<>();

        // Calculate sizes of collections and update total size
        for (Map.Entry<String, CollectionMetadata> entry : collections.entrySet()) {
            long collectionSize = entry.getValue().getTotalSize();
            collectionSizes.put(entry.getKey(), collectionSize);
            totalSize += collectionSize;
        }

        // Calculate size of untagged files
        long untaggedFilesSize = files.stream()
                .filter(file -> !isFileInAnyCollection(file))
                .mapToLong(FileMetadata::getSize)
                .sum();

        totalSize += untaggedFilesSize; // Add untagged files to the total size

        // Generate top N collections
        Map<String, Long> topNMap = new LinkedHashMap<>();
        for (int i = 0; i < topNCollections && !topCollections.isEmpty(); i++) {
            Map.Entry<String, Long> entry = topCollections.poll();
            topNMap.put(entry.getKey(), entry.getValue());
        }

        return new Report(totalSize, topNMap, untaggedFilesSize);
    }

    private boolean isFileInAnyCollection(FileMetadata file) {
        for (CollectionMetadata collection : collections.values()) {
            if (collection.getFiles().contains(file)) {
                return true;
            }
        }
        return false;
    }
}