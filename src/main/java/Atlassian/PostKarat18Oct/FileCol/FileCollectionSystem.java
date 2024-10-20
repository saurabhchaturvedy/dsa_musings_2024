package Atlassian.PostKarat18Oct.FileCol;

import java.util.Arrays;
import java.util.List;

public class FileCollectionSystem {
    public static void main(String[] args) {
        // Sample file data
        List<FileMetadata> files = Arrays.asList(
            new FileMetadata("file1.txt", 100, Arrays.asList("collection1")),
            new FileMetadata("file2.txt", 200, Arrays.asList("collection1", "collection2")),
            new FileMetadata("file3.txt", 300, Arrays.asList("collection2", "collection3")),
            new FileMetadata("file4.txt", 150, Arrays.asList("collection3")),
            new FileMetadata("file5.txt", 50, null) // No collection
        );

        // Create a report generator and generate report for top 3 collections
        CollectionReportGenerator reportGenerator = new CollectionReportGenerator();
        reportGenerator.generateReport(files, 3);
    }
}