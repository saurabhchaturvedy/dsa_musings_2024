package Atlassian.PostKaratRev1.FileCollection2PQ;

public class FileMetadata {



    String fileName;

    int fileSize;

    String collectionId;

    public FileMetadata(String fileName, int fileSize,String collectionId) {
        this.fileName = fileName;
        this.collectionId = collectionId;
        this.fileSize = fileSize;
    }
}
