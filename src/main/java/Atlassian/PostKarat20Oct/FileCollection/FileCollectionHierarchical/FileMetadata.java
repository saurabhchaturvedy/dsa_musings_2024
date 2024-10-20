package Atlassian.PostKarat20Oct.FileCollection.FileCollectionHierarchical;

public class FileMetadata {


    String fileName;
    int size;


    public FileMetadata(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }
}
