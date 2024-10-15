package Atlassian.PostKaratRev1.FileCol2;

import java.util.Map;

public class Report {


    long totalSize;

    Map<String, Long> collectionSizes;


    Report(long totalSize, Map<String, Long> collectionSizes) {


        this.totalSize = totalSize;
        this.collectionSizes = collectionSizes;
    }


    public long getTotalSize() {
        return totalSize;
    }

    public Map<String, Long> getCollectionSizes() {
        return collectionSizes;
    }


    public String toString() {


        StringBuilder sb = new StringBuilder();

        sb.append(" Total Size : ").append(totalSize).append("\n");
        sb.append(" Collections Size : \n");

        for (Map.Entry<String, Long> entry : collectionSizes.entrySet()) {

            sb.append(" Collection : ").append(entry.getKey()).append("\n");
            sb.append("Size : ").append(entry.getValue()).append("\n");

        }

        return sb.toString();
    }
}
