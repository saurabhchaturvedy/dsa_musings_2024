package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel1;

import java.util.Map;

class Report {
    private long totalSize;
    private Map<String, Long> collectionSizes;

    public Report(long totalSize, Map<String, Long> collectionSizes) {
        this.totalSize = totalSize;
        this.collectionSizes = collectionSizes;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public Map<String, Long> getCollectionSizes() {
        return collectionSizes;
    }

    @Override
    public String toString() {
        return "Total Size: " + totalSize + ", Collection Sizes: " + collectionSizes;
    }
}