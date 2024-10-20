package Atlassian.PostKarat20Oct.FileCollection.FileCollectionUnTaggedFiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class CollectionMetadata {
    private String id;
    private long totalSize; // Maintain running total size of files
    private Set<FileMetadata> files; // Use a set for O(1) lookups
    private List<CollectionMetadata> childCollections;

    public CollectionMetadata(String id) {
        this.id = id;
        this.totalSize = 0;
        this.files = new HashSet<>();
        this.childCollections = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addFile(FileMetadata file) {
        if (files.add(file)) { // Only add if not already present
            totalSize += file.getSize();
        }
    }

    public void addChildCollection(CollectionMetadata collection) {
        childCollections.add(collection);
    }

    public long calculateTotalSize() {
        long childSize = 0;
        for (CollectionMetadata child : childCollections) {
            childSize += child.calculateTotalSize();
        }
        return totalSize + childSize;
    }

    public long getTotalSize() {
        return totalSize; // Return the maintained size
    }

    public Set<FileMetadata> getFiles() {
        return files;
    }

    public List<CollectionMetadata> getChildCollections() {
        return childCollections;
    }


}