package Atlassian.PostKaratRev1.FileCollection.FileCollectionPQ;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionToFileSizeMap;


    Report(long totalFileSize, Map<String, Long> collectionToFileSizeMap) {

        this.totalFileSize = totalFileSize;
        this.collectionToFileSizeMap = collectionToFileSizeMap;
    }
}