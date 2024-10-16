package Atlassian.PostKaratRev1.FileCollection.NestedCollections;

import java.util.ArrayList;
import java.util.List;

public class Collection {


    String collectionId;

    List<FileMetadata> files;

    List<Collection> collections;


    Collection(String collectionId) {

        this.collectionId = collectionId;
        this.files = new ArrayList<>();
        this.collections = new ArrayList<>();
    }


    public void addFile(FileMetadata fileMetadata) {

        files.add(fileMetadata);
    }

    public void addCollection(Collection collection) {

        collections.add(collection);
    }
}
