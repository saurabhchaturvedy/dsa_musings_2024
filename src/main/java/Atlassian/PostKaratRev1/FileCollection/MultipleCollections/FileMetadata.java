package Atlassian.PostKaratRev1.FileCollection.MultipleCollections;

import java.util.List;
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
