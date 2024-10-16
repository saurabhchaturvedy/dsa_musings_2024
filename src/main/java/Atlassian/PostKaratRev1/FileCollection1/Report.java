package Atlassian.PostKaratRev1.FileCollection1;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionSizes;


    Report(long totalFileSize, Map<String, Long> collectionSizes) {

        this.totalFileSize = totalFileSize;
        this.collectionSizes = collectionSizes;
    }
}
