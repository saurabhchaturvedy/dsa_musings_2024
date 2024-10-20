package Atlassian.PostKarat20Oct.FileCollection.FileCollectionHierarchical;

import java.util.Map;

public class Report {


    long totalFileSize;

    Map<String, Long> collectionFilesMap;


    public Report(long totalFileSize, Map<String, Long> collectionFilesMap) {
        this.totalFileSize = totalFileSize;
        this.collectionFilesMap = collectionFilesMap;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(" Total File Size : ").append(totalFileSize).append("\n");
        for (Map.Entry<String, Long> entry : collectionFilesMap.entrySet()) {

            sb.append("Collection Name : ").append(entry.getKey());
            sb.append(" <> Size of Files in Collection : ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
