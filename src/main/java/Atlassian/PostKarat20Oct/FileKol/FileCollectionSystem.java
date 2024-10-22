package Atlassian.PostKarat20Oct.FileKol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileCollectionSystem {
    private Map<String, File> files;
    private Map<String, Collection> collections;
    private int totalFileSize;

    public FileCollectionSystem() {
        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.totalFileSize = 0;
    }

    // Add a new file, optionally tagging it to one or more collections
    public void addFile(String fileName, int size, List<String> collectionNames) {
        File file = new File(fileName, size);
        files.put(fileName, file);
        totalFileSize += size;

        // If file belongs to collections, add it to those collections
        if (collectionNames != null) {
            for (String collectionName : collectionNames) {
                Collection collection = collections.computeIfAbsent(collectionName, k -> new Collection(k));
                collection.addFile(file);
            }
        }
    }

    // Add a collection to another collection (parent -> child relationship)
    public void addCollectionToCollection(String childName, String parentName) {
        Collection child = collections.computeIfAbsent(childName, k -> new Collection(k));
        Collection parent = collections.computeIfAbsent(parentName, k -> new Collection(k));
        parent.addChildCollection(child);
    }

    // Get the total size of all files in the system
    public int getTotalFileSize() {
        return totalFileSize;
    }

    // Get the top N collections by their total size (including child collections)
    public List<String> getTopNCollections(int N) {
        // Calculate size for each collection
        Map<String, Integer> collectionSizes = new HashMap<>();
        for (Collection collection : collections.values()) {
            collectionSizes.put(collection.getName(), collection.getTotalSize());
        }

        // Sort collections by size in descending order
        List<Map.Entry<String, Integer>> sortedCollections = new ArrayList<>(collectionSizes.entrySet());
        sortedCollections.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // Return the top N collection names
        List<String> topCollections = new ArrayList<>();
        for (int i = 0; i < Math.min(N, sortedCollections.size()); i++) {
            topCollections.add(sortedCollections.get(i).getKey());
        }
        return topCollections;
    }
}