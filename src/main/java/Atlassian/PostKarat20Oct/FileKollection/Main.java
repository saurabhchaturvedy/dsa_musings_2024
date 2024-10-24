package Atlassian.PostKarat20Oct.FileKollection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        FileCollectionSystem fileCollectionSystem = new FileCollectionSystem();

        fileCollectionSystem.addFile("f1.txt", 100, null);
        fileCollectionSystem.addFile("f2.txt", 200, Arrays.asList("c1", "c2"));
        fileCollectionSystem.addFile("f3.txt", 200, Collections.singletonList("c1"));
        fileCollectionSystem.addFile("f4.txt", 300, Collections.singletonList("c2"));
        fileCollectionSystem.addFile("f5.txt", 10, Collections.singletonList("c3"));

        // Adding collections
        fileCollectionSystem.addCollection("c3", "c1");

        // Generate reports
        System.out.println("Total file size: " + fileCollectionSystem.getTotalFileSize());
        List<String> topCollections = fileCollectionSystem.getTopK(2);
        System.out.println("Top 2 collections by size: " + topCollections);
    }
}
