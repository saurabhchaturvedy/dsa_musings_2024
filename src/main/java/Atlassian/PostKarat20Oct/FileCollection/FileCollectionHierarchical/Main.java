package Atlassian.PostKarat20Oct.FileCollection.FileCollectionHierarchical;

public class Main {


    public static void main(String[] args) {


        FileStorageSystem fileStorageSystem = new FileStorageSystem();


        fileStorageSystem.addFileToCollection("collection1", new FileMetadata("file1.txt", 200));
        fileStorageSystem.addFileToCollection("collection2", new FileMetadata("file2.txt", 120));
        fileStorageSystem.addFileToCollection("collection3", new FileMetadata("file3.txt", 80));

        CollectionMetadata collection = new CollectionMetadata("childCollection1");

        fileStorageSystem.addFileToCollection("childCollection1", new FileMetadata("file4.txt", 350));

        fileStorageSystem.addChildCollection("collection2", collection);


        Report report = fileStorageSystem.generateReport(3);

        System.out.println(report);


    }
}
