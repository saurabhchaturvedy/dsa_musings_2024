package Atlassian.PostKaratRev1.FileCol;

import java.util.Map;

public class Report {


    long totalSize;

    Map<String, Long> collections;


    Report(long totalSize, Map<String, Long> collections) {


        this.totalSize = totalSize;
        this.collections = collections;
    }


    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(" Total Size : ").
                append(totalSize).append("\n");
        for (Map.Entry<String, Long> entry : collections.entrySet()) {

            sb.append(" Collection : ").append(entry.getKey()).append(" Size : ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
