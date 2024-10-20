package Atlassian.PostKarat20Oct.FileCollection.FileCollection1;

import java.util.List;

public class FileMetadata {


    String fileName;

    long size;

    List<String> collections;


    public FileMetadata(String fileName, long size, List<String> collections) {
        this.fileName = fileName;
        this.size = size;
        this.collections = collections;
    }


    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public List<String> getCollections() {
        return collections;
    }
}
