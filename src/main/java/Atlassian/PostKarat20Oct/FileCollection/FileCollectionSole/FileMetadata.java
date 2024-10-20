package Atlassian.PostKarat20Oct.FileCollection.FileCollectionSole;

public class FileMetadata {


    String fileName;
    long size;
    String collectionId;

    public FileMetadata(String fileName, long size, String collectionId) {
        this.fileName = fileName;
        this.collectionId = collectionId;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getFileName() {
        return fileName;
    }
}
