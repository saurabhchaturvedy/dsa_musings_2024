package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel2;

public class Main {
    public static void main(String[] args) {
        FileStorageSystem storageSystem = new FileStorageSystem();

        // Adding files (some untagged)
        storageSystem.addFile(new FileMetadata("file1.txt", 180));
        storageSystem.addFile(new FileMetadata("file2.txt", 200));
        storageSystem.addFile(new FileMetadata("file3.txt", 200));

        // Adding files to collections
        storageSystem.addFileToCollection("collection1", new FileMetadata("file4.txt", 300));
        storageSystem.addFileToCollection("collection2", new FileMetadata("file5.txt", 10));

        // Create a child collection
        CollectionMetadata childCollection = new CollectionMetadata("childCollection1");
        childCollection.addFile(new FileMetadata("file6.txt", 50));
        storageSystem.addChildCollection("collection1", childCollection);

        // Generating report for top 2 collections
        Report report = storageSystem.generateReport(2);
        System.out.println(report);
    }
}