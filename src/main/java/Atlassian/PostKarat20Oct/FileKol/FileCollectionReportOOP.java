package Atlassian.PostKarat20Oct.FileKol;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileCollectionReportOOP {
    public static void main(String[] args) {
        FileCollectionSystem system = new FileCollectionSystem();

        // Example input
        system.addFile("f1.txt", 100, null);
        system.addFile("f2.txt", 200, Arrays.asList("c1", "c2"));
        system.addFile("f3.txt", 200, Collections.singletonList("c1"));
        system.addFile("f4.txt", 300, Collections.singletonList("c2"));
        system.addFile("f5.txt", 10, Collections.singletonList("c3"));
        system.addCollectionToCollection("c3", "c1");
        system.addCollectionToCollection("c2", "c1");

        // Generate reports
        System.out.println("Total file size: " + system.getTotalFileSize());
        List<String> topCollections = system.getTopNCollections(2);
        System.out.println("Top 2 collections by size: " + topCollections);
    }
}