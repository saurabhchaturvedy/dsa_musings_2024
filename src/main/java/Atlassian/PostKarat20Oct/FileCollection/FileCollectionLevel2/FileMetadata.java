package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel2;

class FileMetadata {
    private String name;
    private long size;

    public FileMetadata(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}