package Atlassian.PostKaratRev1.FileCol2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) {


        List<FileMetadata> files = Arrays.asList(
                new FileMetadata("file1.txt", 180, Arrays.asList("collection1")),
                new FileMetadata("file2.txt", 200, Arrays.asList("collection")),
                new FileMetadata("file3.txt", 200, Arrays.asList("collection")),
                new FileMetadata("file4.txt", 300, Arrays.asList("collection", "collection2")),
                new FileMetadata("file5.txt", 10, Arrays.asList())
        );


        Collection collection1 = new Collection("collection");
        Collection collection2 = new Collection("collection1");
        Collection collection3 = new Collection("collection2");
        Collection collection4 = new Collection("collection3");



        Map<String, Collection> collections = new HashMap<>();
        collections.put("collection", collection1);
        collections.put("collection1", collection2);
        collections.put("collection2", collection3);
        collections.put("collection3", collection4);

        FileReportService fileReportService = new FileReportService();


        Report report = fileReportService.generateReport(files, collections);

        System.out.println(report);
    }
}
