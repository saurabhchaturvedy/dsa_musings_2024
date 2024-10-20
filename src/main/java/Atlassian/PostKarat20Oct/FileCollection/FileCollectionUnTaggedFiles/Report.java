package Atlassian.PostKarat20Oct.FileCollection.FileCollectionUnTaggedFiles;

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

        StringBuilder sb = new StringBuilder();
        sb.append(" Total File Size : ").append(totalSize).append("\n");
        for (Map.Entry<String, Long> entry : collectionSizes.entrySet()) {

            sb.append("Collection Name : ").append(entry.getKey());
            sb.append(" <> Size of Files in Collection : ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
