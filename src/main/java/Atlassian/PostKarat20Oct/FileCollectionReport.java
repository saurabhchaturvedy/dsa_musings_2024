package Atlassian.PostKarat20Oct;

import java.util.*;

public class FileCollectionReport {

    // File sizes
    private static Map<String, Integer> fileSizes = new HashMap<>();
    
    // Collection to file mapping
    private static Map<String, List<String>> collectionToFiles = new HashMap<>();
    
    // Collection hierarchy mapping (parent -> children)
    private static Map<String, List<String>> collectionHierarchy = new HashMap<>();
    
    // Cache to store calculated collection sizes
    private static Map<String, Integer> collectionSizesCache = new HashMap<>();
    
    // Total size of all files
    private static int totalFileSize = 0;

    public static void main(String[] args) {
        // Example input
        addFile("f1.txt", 100, null);
        addFile("f2.txt", 200, Arrays.asList("c1", "c2"));
        addFile("f3.txt", 200, Collections.singletonList("c1"));
        addFile("f4.txt", 300, Collections.singletonList("c2"));
        addFile("f5.txt", 10, Collections.singletonList("c3"));
        addCollectionToCollection("c3", "c1");

        // Generate reports
        System.out.println("Total file size: " + getTotalFileSize());
        List<String> topCollections = getTopNCollections(2);
        System.out.println("Top 2 collections by size: " + topCollections);
    }

    // Adds a file to the system, optionally tagging it to collections
    private static void addFile(String fileName, int size, List<String> collections) {
        fileSizes.put(fileName, size);
        totalFileSize += size;

        if (collections != null) {
            for (String collection : collections) {
                collectionToFiles.computeIfAbsent(collection, k -> new ArrayList<>()).add(fileName);
            }
        }
    }

    // Adds a collection to another collection (parent -> child relationship)
    private static void addCollectionToCollection(String child, String parent) {
        collectionHierarchy.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
    }

    // Gets the total size of all files in the system
    private static int getTotalFileSize() {
        return totalFileSize;
    }

    // Returns the top N collections sorted by their total size (including child collections)
    private static List<String> getTopNCollections(int N) {
        // Calculate size for each collection
        Map<String, Integer> collectionSizes = new HashMap<>();
        for (String collection : collectionToFiles.keySet()) {
            collectionSizes.put(collection, calculateCollectionSize(collection));
        }
        
        // Sort collections by size in descending order
        List<Map.Entry<String, Integer>> sortedCollections = new ArrayList<>(collectionSizes.entrySet());
        sortedCollections.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        // Get the top N collections
        List<String> topCollections = new ArrayList<>();
        for (int i = 0; i < Math.min(N, sortedCollections.size()); i++) {
            topCollections.add(sortedCollections.get(i).getKey());
        }
        return topCollections;
    }

    // Recursively calculates the total size of a collection, including its child collections
    private static int calculateCollectionSize(String collection) {
        if (collectionSizesCache.containsKey(collection)) {
            return collectionSizesCache.get(collection);
        }

        // Sum size of all files in this collection
        int size = 0;
        if (collectionToFiles.containsKey(collection)) {
            for (String file : collectionToFiles.get(collection)) {
                size += fileSizes.get(file);
            }
        }

        // Sum size of all child collections
        if (collectionHierarchy.containsKey(collection)) {
            for (String child : collectionHierarchy.get(collection)) {
                size += calculateCollectionSize(child);
            }
        }

        // Memoize the result
        collectionSizesCache.put(collection, size);
        return size;
    }
}
