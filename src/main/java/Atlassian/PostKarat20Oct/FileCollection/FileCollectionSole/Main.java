package Atlassian.PostKarat20Oct.FileCollection.FileCollectionSole;

public class Main {


    public static void main(String[] args) {


        FileStorageSystem fileStorageSystem = new FileStorageSystem();


        fileStorageSystem.addFile(new FileMetadata("file1.txt", 100, null));
        fileStorageSystem.addFile(new FileMetadata("file2.txt", 200, "collection1"));
        fileStorageSystem.addFile(new FileMetadata("file3.txt", 200, "collection1"));
        fileStorageSystem.addFile(new FileMetadata("file4.txt", 300, "collection2"));
        fileStorageSystem.addFile(new FileMetadata("file5.txt", 10, null));


        Report report = fileStorageSystem.generateReport(2);

        System.out.println(report);
    }
}
