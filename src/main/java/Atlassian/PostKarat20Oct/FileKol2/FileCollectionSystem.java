package Atlassian.PostKarat20Oct.FileKol2;

import java.util.*;

class FileCollectionSystem {
    private Map<String, File> files;
    private Map<String, Collection> collections;
    private int totalFileSize;
    private TreeSet<Collection> sortedCollections; // To maintain sorted order of collections

    public FileCollectionSystem() {
        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.totalFileSize = 0;
        this.sortedCollections = new TreeSet<>((a, b) -> {
            int sizeComparison = Integer.compare(b.getTotalSize(), a.getTotalSize());
            if (sizeComparison == 0) {
                return a.getName().compareTo(b.getName()); // To ensure consistent ordering if sizes are equal
            }
            return sizeComparison;
        });
    }

    // Add a single file, optionally tagging it to one or more collections
    public void addFile(String fileName, int size, List<String> collectionNames) {
        File file = new File(fileName, size);
        files.put(fileName, file);
        totalFileSize += size;

        // If file belongs to collections, add it to those collections
        if (collectionNames != null) {
            for (String collectionName : collectionNames) {
                Collection collection = collections.computeIfAbsent(collectionName, k -> new Collection(k));
                collection.addFile(file);
                updateSortedCollections(collection);
            }
        }
    }

    // Add a collection to another collection (parent -> child relationship)
    public void addCollection(String childName, String parentName) {
        Collection child = collections.computeIfAbsent(childName, k -> new Collection(k));
        Collection parent = collections.computeIfAbsent(parentName, k -> new Collection(k));
        parent.addChildCollection(child);
        updateSortedCollections(parent);
    }

    // Efficiently update the sorted list of collections
    private void updateSortedCollections(Collection collection) {
        sortedCollections.remove(collection); // Remove old version
        collection.getTotalSize(); // Update the collection size (recalculate if needed)
        sortedCollections.add(collection); // Re-add to TreeSet with updated size
    }

    // Get the total size of all files in the system
    public int getTotalFileSize() {
        return totalFileSize;
    }

    // Get the top N collections by their total size (including child collections)
    public List<String> getTopNCollections(int N) {
        List<String> topCollections = new ArrayList<>();
        int count = 0;
        for (Collection collection : sortedCollections) {
            if (count >= N) break;
            topCollections.add(collection.getName());
            count++;
        }
        return topCollections;
    }
}
