package Atlassian.PostKarat20Oct.FileCollection.FileCollection2;

import java.util.ArrayList;
import java.util.List;

public class Collection {


    String collection;

    List<FileMetadata> files;

    List<Collection> collections;


    Collection(String collection) {

        this.collection = collection;
        this.files = new ArrayList<>();
        this.collections = new ArrayList<>();
    }


    public void addFile(FileMetadata file) {

        this.files.add(file);
    }

    public void addCollection(Collection collection) {

        this.collections.add(collection);
    }


    public int getTotalFileSize() {

        int totalFileSize = 0;

        for (FileMetadata file : files) {

            totalFileSize = totalFileSize + file.getSize();

        }

        for (Collection collection : this.collections) {

            totalFileSize = totalFileSize + this.getTotalFileSize();
        }

        return totalFileSize;
    }
}
