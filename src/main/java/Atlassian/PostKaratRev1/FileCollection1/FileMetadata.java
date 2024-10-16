package Atlassian.PostKaratRev1.FileCollection1;

public class FileMetadata {


    String fileName;

    int fileSize;

    String collectionId;


    FileMetadata(String fileName, int fileSize, String collectionId) {

        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionId = collectionId;
    }
}
