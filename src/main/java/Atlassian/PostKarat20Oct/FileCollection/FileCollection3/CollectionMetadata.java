package Atlassian.PostKarat20Oct.FileCollection.FileCollection3;

import java.util.ArrayList;
import java.util.List;

class CollectionMetadata {
    String collectionName;
    List<FileMetadata> files;
    List<CollectionMetadata> childCollections;

    public CollectionMetadata(String collectionName) {
        this.collectionName = collectionName;
        this.files = new ArrayList<>();
        this.childCollections = new ArrayList<>();
    }

    public void addFile(FileMetadata file) {
        this.files.add(file);
    }

    public void addChildCollection(CollectionMetadata collection) {
        this.childCollections.add(collection);
    }

    // Recursive method to calculate the total size of the collection (files + child collections)
    public long getTotalSize() {
        long totalSize = 0;

        // Add the size of files in this collection
        for (FileMetadata file : files) {
            totalSize += file.size;
        }

        // Recursively add the size of child collections
        for (CollectionMetadata child : childCollections) {
            totalSize += child.getTotalSize();
        }

        return totalSize;
    }
}