package Atlassian.PostKaratRev1.FileCol;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        List<FileMetadata> files = Arrays.asList(
                new FileMetadata("file1.txt", 100, null),
                new FileMetadata("file2.txt", 200, "collection1"),
                new FileMetadata("file3.txt", 200, "collection1"),
                new FileMetadata("file4.txt", 300, "collection2"),
                new FileMetadata("file5.txt", 10, null)
        );


        FileStorageSystem fileStorageSystem = new FileStorageSystem();


        Report report = fileStorageSystem.generateReport(files, 2);

        System.out.println(report);
    }
}
