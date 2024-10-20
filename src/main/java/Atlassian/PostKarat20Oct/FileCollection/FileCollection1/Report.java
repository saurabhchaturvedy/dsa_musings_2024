package Atlassian.PostKarat20Oct.FileCollection.FileCollection1;

import java.util.List;
import java.util.Map;

public class Report {


    long totalFileSize;

    List<Map.Entry<String, Long>> topNCollections;

    public Report(List<Map.Entry<String, Long>> topNCollections, long totalFileSize) {
        this.topNCollections = topNCollections;
        this.totalFileSize = totalFileSize;
    }


    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(" Total Files Size : ").append(this.totalFileSize).append("\n");
        sb.append(" Collections and File Size : ").append("\n");

        for (Map.Entry<String, Long> entry : this.topNCollections) {

            sb.append(" Collection Name => ").append(entry.getKey()).append(",");
            sb.append(" File Size => ").append(entry.getValue()).append("\n");

        }

        return sb.toString();
    }
}
