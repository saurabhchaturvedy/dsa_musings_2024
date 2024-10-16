package Atlassian.PostKaratRev1.FileCollection.NestedCollections;

import java.util.Set;

public class FileMetadata {


    String fileName;

    long fileSize;

    Set<String> collectionIds;


    public FileMetadata(String fileName, long fileSize, Set<String> collectionIds) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collectionIds = collectionIds;
    }
}
