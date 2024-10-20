package Atlassian.PostKarat20Oct.FileCollection.FileCollectionMultiple;


import java.util.*;

public class FileStorageSystem {


    List<FileMetadata> files;


    FileStorageSystem() {

        this.files = new ArrayList<>();
    }


    public void addFile(FileMetadata file) {

        this.files.add(file);
    }


    public Report generateReport(int topK) {


        long totalFileSize = 0;

        Map<String, Long> map = new HashMap<>();

        for (FileMetadata file : this.files) {

            totalFileSize += file.getSize();

            if(file.getCollectionIds()!=null) {
                for (String collectionId : file.getCollectionIds()) {
                    if (collectionId != null) {

                        map.put(collectionId, map.getOrDefault(collectionId, 0L) + file.getSize());
                    }
                }
            }
        }


        Map<String, Long> topNCollections = getTopNCollections(map, topK);

        return new Report(totalFileSize, topNCollections);
    }

    private Map<String, Long> getTopNCollections(Map<String, Long> map, int topK) {


        List<Map.Entry<String, Long>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

        Map<String, Long> topNCollections = new LinkedHashMap<>(topK);

        for (int i = 0; i < Math.min(topK, sortedList.size()); i++) {

            Map.Entry<String, Long> entry = sortedList.get(i);
            topNCollections.put(entry.getKey(), entry.getValue());
        }

        return topNCollections;
    }
}
