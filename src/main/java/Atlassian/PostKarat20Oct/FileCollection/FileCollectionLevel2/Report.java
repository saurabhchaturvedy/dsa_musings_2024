package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel2;

import java.util.Map;

class Report {
    private long totalSize;
    private Map<String, Long> collectionSizes;
    private long untaggedFilesSize;

    public Report(long totalSize, Map<String, Long> collectionSizes, long untaggedFilesSize) {
        this.totalSize = totalSize;
        this.collectionSizes = collectionSizes;
        this.untaggedFilesSize = untaggedFilesSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public Map<String, Long> getCollectionSizes() {
        return collectionSizes;
    }

    public long getUntaggedFilesSize() {
        return untaggedFilesSize;
    }

    @Override
    public String toString() {
        return "Total Size: " + totalSize + 
               ", Collection Sizes: " + collectionSizes + 
               ", Untagged Files Size: " + untaggedFilesSize;
    }
}