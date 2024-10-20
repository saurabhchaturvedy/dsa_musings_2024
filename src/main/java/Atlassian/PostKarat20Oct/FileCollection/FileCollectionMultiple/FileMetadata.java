package Atlassian.PostKarat20Oct.FileCollection.FileCollectionMultiple;

import java.util.List;

public class FileMetadata {

    String fileName;
    long size;
    List<String> collectionIds;

    public FileMetadata(String fileName, long size, List<String> collectionId) {
        this.fileName = fileName;
        this.collectionIds = collectionId;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public List<String> getCollectionIds() {
        return collectionIds;
    }

    public String getFileName() {
        return fileName;
    }
}
