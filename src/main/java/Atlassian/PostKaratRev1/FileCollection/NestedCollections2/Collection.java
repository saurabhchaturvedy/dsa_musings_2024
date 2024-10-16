package Atlassian.PostKaratRev1.FileCollection.NestedCollections2;

import java.util.ArrayList;
import java.util.List;

public class Collection {


    String collectionId;

    List<String> collectionIds;


    Collection(String collectionId) {

        this.collectionId = collectionId;
        this.collectionIds = new ArrayList<>();
    }


    public void addCollection(String collectionId) {

        this.collectionIds.add(collectionId);
    }
}
