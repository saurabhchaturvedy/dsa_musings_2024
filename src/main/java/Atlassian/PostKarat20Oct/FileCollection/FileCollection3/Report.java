package Atlassian.PostKarat20Oct.FileCollection.FileCollection3;

import java.util.List;
import java.util.Map;

class Report {
    long totalSize;
    List<Map.Entry<String, Long>> topCollections; // List of top N collections by size

    public Report(long totalSize, List<Map.Entry<String, Long>> topCollections) {
        this.totalSize = totalSize;
        this.topCollections = topCollections;
    }

    public void printReport() {
        System.out.println("Total size of all files: " + totalSize);
        System.out.println("Top collections by size:");
        for (Map.Entry<String, Long> entry : topCollections) {
            System.out.println("Collection: " + entry.getKey() + ", Size: " + entry.getValue());
        }
    }
}