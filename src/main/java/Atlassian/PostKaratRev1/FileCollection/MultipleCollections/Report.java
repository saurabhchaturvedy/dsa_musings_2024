package Atlassian.PostKaratRev1.FileCollection.MultipleCollections;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionToFileSizeMap;


    public Report(long totalFileSize, Map<String, Long> collectionToFileSizeMap) {
        this.totalFileSize = totalFileSize;
        this.collectionToFileSizeMap = collectionToFileSizeMap;
    }
}
