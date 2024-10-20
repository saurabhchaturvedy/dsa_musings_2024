package Atlassian.PostKarat20Oct.FileCollection.FileCollectionLevel1;

class FileMetadata {
    private String name;
    private long size;
    private String collectionId;

    public FileMetadata(String name, long size, String collectionId) {
        this.name = name;
        this.size = size;
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getCollectionId() {
        return collectionId;
    }
}