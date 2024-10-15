package Atlassian.PostKaratRev1.FileCol;

public class FileMetadata {


    String fileName;

    long fileSize;

    String collectionId;


    FileMetadata(String fileName, long fileSize, String collectionId) {

        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionId = collectionId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public long getFileSize() {
        return fileSize;
    }
}
