package Atlassian.PostKaratRev1.FileCol2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collection {


    String name;

    List<Collection> collections;

    Set<String> files;


    Collection(String name) {

        this.name = name;
        this.collections = new ArrayList<>();
        this.files = new HashSet<>();
    }


    public void addChildCollection(Collection collection) {

        this.collections.add(collection);
    }

    public void addFile(String name) {

        this.files.add(name);
    }

    public Set<String> getFiles() {
        return files;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public String getName() {
        return name;
    }
}
