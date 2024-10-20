package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel1;

import java.util.*;

class FileStorageSystem {
    private List<FileMetadata> files;

    public FileStorageSystem() {
        this.files = new ArrayList<>();
    }

    public void addFile(FileMetadata file) {
        files.add(file);
    }

    public Report generateReport(int topNCollections) {
        long totalSize = 0;
        Map<String, Long> collectionSizes = new HashMap<>();

        for (FileMetadata file : files) {
            totalSize += file.getSize();
            collectionSizes.merge(file.getCollectionId(), file.getSize(), Long::sum);
        }

        return createTopCollectionsReport(totalSize, collectionSizes, topNCollections);
    }

    private Report createTopCollectionsReport(long totalSize, Map<String, Long> collectionSizes, int topN) {
        // Sort collections by size
        List<Map.Entry<String, Long>> sortedCollections = new ArrayList<>(collectionSizes.entrySet());
        sortedCollections.sort((a, b) -> Long.compare(b.getValue(), a.getValue())); // Descending order

        // Create a map for the top N collections
        Map<String, Long> topCollections = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(topN, sortedCollections.size()); i++) {
            Map.Entry<String, Long> entry = sortedCollections.get(i);
            topCollections.put(entry.getKey(), entry.getValue());
        }

        return new Report(totalSize, topCollections);
    }
}