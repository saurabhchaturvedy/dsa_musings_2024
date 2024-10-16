package Atlassian.PostKaratRev1.FileCollection.FileCollectionPQ;

public class FileMetadata {


    String fileName;

    long fileSize;

    String collectionId;


    FileMetadata(String fileName, long fileSize, String collectionId) {


        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionId = collectionId;
    }
}
