package Atlassian.PostKaratRev1.FileCol2;

import java.util.List;

public class FileMetadata {


    String fileName;

    int fileSize;

    List<String> collectionIds;


    FileMetadata(String fileName, int fileSize, List<String> collectionIds) {

        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionIds = collectionIds;
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public List<String> getCollectionIds() {
        return collectionIds;
    }
}
