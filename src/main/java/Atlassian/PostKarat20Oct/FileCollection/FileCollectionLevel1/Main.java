package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel1;

public class Main {
    public static void main(String[] args) {
        FileStorageSystem storageSystem = new FileStorageSystem();
        
        // Adding files to the storage system
        storageSystem.addFile(new FileMetadata("file1.txt", 100, "collection1"));
        storageSystem.addFile(new FileMetadata("file2.txt", 200, "collection1"));
        storageSystem.addFile(new FileMetadata("file3.txt", 200, "collection1"));
        storageSystem.addFile(new FileMetadata("file4.txt", 300, "collection2"));
        storageSystem.addFile(new FileMetadata("file5.txt", 10, null));

        // Generating report for top 2 collections
        Report report = storageSystem.generateReport(2);
        System.out.println(report);
    }
}