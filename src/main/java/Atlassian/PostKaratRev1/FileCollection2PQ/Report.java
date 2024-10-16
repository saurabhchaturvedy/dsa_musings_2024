package Atlassian.PostKaratRev1.FileCollection2PQ;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionSizes;


    Report(long totalFileSize, Map<String, Long> collectionSizes) {


        this.totalFileSize = totalFileSize;
        this.collectionSizes = collectionSizes;
    }
}
