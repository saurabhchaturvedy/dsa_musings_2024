package Atlassian.PostKaratRev1.FileCollection.NestedCollections2;

import java.util.Set;

public class File {


    String fileName;
    long fileSize;

    Set<String> collectionIds;


    public File(String fileName, long fileSize,Set<String> collectionIds) {
        this.fileName = fileName;
        this.collectionIds = collectionIds;
        this.fileSize = fileSize;
    }
}
