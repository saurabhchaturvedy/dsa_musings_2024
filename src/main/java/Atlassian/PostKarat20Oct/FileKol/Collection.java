package Atlassian.PostKarat20Oct.FileKol;

import java.util.ArrayList;
import java.util.List;

class Collection {
    private String name;
    private List<File> files; // Files in this collection
    private List<Collection> childCollections; // Child collections
    private int cachedSize; // Memoized size of the collection

    public Collection(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.childCollections = new ArrayList<>();
        this.cachedSize = -1; // -1 indicates that the size has not been calculated yet
    }

    public String getName() {
        return name;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addChildCollection(Collection collection) {
        childCollections.add(collection);
    }

    // Recursively calculate the total size of the collection, including its child collections
    public int getTotalSize() {
        if (cachedSize != -1) {
            return cachedSize; // Return cached size if already calculated
        }

        int totalSize = 0;
        // Add size of all files in this collection
        for (File file : files) {
            totalSize += file.getSize();
        }
        // Add size of all child collections
        for (Collection child : childCollections) {
            totalSize += child.getTotalSize();
        }
        cachedSize = totalSize; // Cache the calculated size
        return totalSize;
    }
}