package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel2;

import java.util.ArrayList;
import java.util.List;

class CollectionMetadata {
    private String id;
    private List<FileMetadata> files;
    private List<CollectionMetadata> childCollections;

    public CollectionMetadata(String id) {
        this.id = id;
        this.files = new ArrayList<>();
        this.childCollections = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addFile(FileMetadata file) {
        files.add(file);
    }

    public void addChildCollection(CollectionMetadata collection) {
        childCollections.add(collection);
    }

    public long calculateTotalSize() {
        long totalSize = 0;

        // Sum the sizes of files in this collection
        for (FileMetadata file : files) {
            totalSize += file.getSize();
        }

        // Recursively sum the sizes of child collections
        for (CollectionMetadata child : childCollections) {
            totalSize += child.calculateTotalSize();
        }

        return totalSize;
    }

    public List<FileMetadata> getFiles() {
        return files;
    }

    public List<CollectionMetadata> getChildCollections() {
        return childCollections;
    }
}