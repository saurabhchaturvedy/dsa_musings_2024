package Atlassian.PostKarat18Oct.FileCol;

import java.util.List;

class FileMetadata {


    private String fileName;
    private long size;
    private List<String> collections;

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
