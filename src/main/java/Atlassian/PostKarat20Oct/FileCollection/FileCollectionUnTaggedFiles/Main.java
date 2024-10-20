package Atlassian.PostKarat20Oct.FileCollection.FileCollectionUnTaggedFiles;


public class Main {
    public static void main(String[] args) {
        FileStorageSystem fileStorageSystem = new FileStorageSystem();

        // Adding files (some untagged)
//        storageSystem.addFile(new FileMetadata("file1.txt", 180));
//        storageSystem.addFile(new FileMetadata("file2.txt", 200));
//        storageSystem.addFile(new FileMetadata("file3.txt", 200));
//
//        // Adding files to collections
//        storageSystem.addFileToCollection("collection1", new FileMetadata("file4.txt", 300));
//        storageSystem.addFileToCollection("collection2", new FileMetadata("file5.txt", 10));
//
//        // Create a child collection
//        CollectionMetadata childCollection = new CollectionMetadata("childCollection1");
//        childCollection.addFile(new FileMetadata("file6.txt", 50));
//        storageSystem.addFileToCollection("childCollection1", new FileMetadata("file6.txt", 50));
//        storageSystem.addChildCollection("collection1", childCollection);

        fileStorageSystem.addFile(new FileMetadata("file1.txt",100));
        fileStorageSystem.addFileToCollection("collection1", new FileMetadata("file2.txt", 200));
        fileStorageSystem.addFileToCollection("collection1", new FileMetadata("file3.txt", 200));
        fileStorageSystem.addFileToCollection("collection3", new FileMetadata("file4.txt", 300));
        fileStorageSystem.addFileToCollection("collection2", new FileMetadata("file4.txt", 300));
        fileStorageSystem.addFile(new FileMetadata("file1.txt",10));

        CollectionMetadata childCollection = new CollectionMetadata("collection2");
        childCollection.addFile(new FileMetadata("file7.txt",300));
        fileStorageSystem.addFileToCollection("collection2",new FileMetadata("file4.txt",300));
        fileStorageSystem.addChildCollection("collection1",childCollection);




        // Generating report for top 2 collections
        Report report = fileStorageSystem.generateReport(3);
        System.out.println(report);
    }
}