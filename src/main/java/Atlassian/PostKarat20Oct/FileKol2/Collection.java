package Atlassian.PostKarat20Oct.FileKol2;

import java.util.ArrayList;
import java.util.List;

class Collection {
    private String name;
    private List<File> files; // Files in this collection
    private List<Collection> childCollections; // Child collections
    private Integer cachedSize; // Memoized size of the collection

    public Collection(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.childCollections = new ArrayList<>();
        this.cachedSize = null; // null indicates that the size has not been calculated yet
    }

    public String getName() {
        return name;
    }

    public void addFile(File file) {
        files.add(file);
        // Invalidate the cache since the size has changed
        cachedSize = null;
    }

    public void addChildCollection(Collection collection) {
        childCollections.add(collection);
        // Invalidate the cache since the size has changed
        cachedSize = null;
    }

    // Recursively calculate the total size of the collection, including its child collections
    public int getTotalSize() {
        if (cachedSize != null) {
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