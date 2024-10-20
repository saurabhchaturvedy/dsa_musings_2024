package Atlassian.PostKarat20Oct.FileCollection.FileCollectionHierarchical;

import java.util.ArrayList;
import java.util.List;

public class CollectionMetadata {


    String id;

    List<FileMetadata> files;
    List<CollectionMetadata> collections;


    CollectionMetadata(String id) {

        this.id = id;
        this.files = new ArrayList<>();
        this.collections = new ArrayList<>();
    }


    public void addFile(FileMetadata file) {

        this.files.add(file);
    }


    public void addChildCollection(CollectionMetadata collection) {

        this.collections.add(collection);
    }


    public long totalFilesSizeInCollection() {

        long totalSize = 0;

        for (FileMetadata file : files) {

            totalSize += file.getSize();
        }


        for (CollectionMetadata collection : collections) {

            totalSize += collection.totalFilesSizeInCollection();
        }

        return totalSize;
    }


    public List<FileMetadata> getFiles() {
        return files;
    }

    public List<CollectionMetadata> getCollections() {
        return collections;
    }
}
