package Atlassian.PostKarat20Oct.FileCollection.FileCollection2;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {


//        FileMetadata file1 = new FileMetadata("file1.txt", 100);
//        FileMetadata file2 = new FileMetadata("file2.txt", 200);
//        FileMetadata file3 = new FileMetadata("file3.txt", 200);
//        FileMetadata file4 = new FileMetadata("file4.txt", 300);
//        FileMetadata file5 = new FileMetadata("file5.txt", 10);
//
//
//        Collection collection1 = new Collection("collection1");
//        collection1.addFile(file2);
//        collection1.addFile(file3);
//
//        Collection collection2 = new Collection("collection2");
//        collection2.addFile(file4);
//
//        Collection collection3 = new Collection("collection3");
//        collection3.addFile(file4);
//
//        collection1.addCollection(collection2);


        FileMetadata file1 = new FileMetadata("file1.txt", 100);
        FileMetadata file2 = new FileMetadata("file2.txt", 200);
        FileMetadata file3 = new FileMetadata("file3.txt", 300);
        FileMetadata file4 = new FileMetadata("file4.txt", 150);
        FileMetadata file5 = new FileMetadata("file5.txt", 50);

        // Creating collections
        Collection collection1 = new Collection("collection1");
        collection1.addFile(file1);
        collection1.addFile(file2);

        Collection collection2 = new Collection("collection2");
        collection2.addFile(file3);

        Collection collection3 = new Collection("collection3");
        collection3.addFile(file4);
        collection3.addFile(file5);

        collection3.addCollection(collection2);

        // Adding sub-collections
        Collection mainCollection = new Collection("Main Collection");
        mainCollection.addCollection(collection1);
        mainCollection.addCollection(collection2);
        mainCollection.addCollection(collection3);

        List<Collection> collections = Arrays.asList(collection1,collection2,collection3);


        FileCollectionReportingService fileCollectionReportingService = new FileCollectionReportingService();

      Report report =  fileCollectionReportingService.generateReport(collections, 3);

        System.out.println(report);


    }
}
