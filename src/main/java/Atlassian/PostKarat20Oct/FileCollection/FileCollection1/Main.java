package Atlassian.PostKarat20Oct.FileCollection.FileCollection1;

import java.util.Arrays;
import java.util.Collections;

public class Main {


    public static void main(String[] args) {


        FileMetadata file1 = new FileMetadata("file1.txt", 100, null);
        FileMetadata file2 = new FileMetadata("file2.txt", 200, Arrays.asList("collection1"));
        FileMetadata file3 = new FileMetadata("file3.txt", 300, Arrays.asList("collection1", "collection2"));
        FileMetadata file4 = new FileMetadata("file4.txt", 80, Arrays.asList("collection3"));
        FileMetadata file5 = new FileMetadata("file5.txt", 150, Arrays.asList("collection2", "collection3"));
        FileMetadata file6 = new FileMetadata("file6.txt", 400, null);


        FileCollectionReportingService fileCollectionReportingService = new FileCollectionReportingService();

        Report report = fileCollectionReportingService.generateReport(Arrays.asList(file1, file2, file3, file4, file5, file6), 2);

        System.out.println(report);
    }
}
