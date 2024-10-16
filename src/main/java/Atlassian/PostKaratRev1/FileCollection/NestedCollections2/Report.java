package Atlassian.PostKaratRev1.FileCollection.NestedCollections2;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionSize;


    public Report(long totalFileSize, Map<String, Long> collectionSize) {
        this.totalFileSize = totalFileSize;
        this.collectionSize = collectionSize;
    }
}
