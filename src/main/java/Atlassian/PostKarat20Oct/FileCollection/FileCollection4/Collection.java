package Atlassian.PostKarat20Oct.FileCollection.FileCollection4;

import java.util.ArrayList;
import java.util.List;

class Collection {
    String name;
    Collection parent; // Parent collection, if any
    List<File> files; // Files that belong to this collection
    List<Collection> childCollections; // Child collections of this collection

    public Collection(String name, Collection parent) {
        this.name = name;
        this.parent = parent;
        this.files = new ArrayList<>();
        this.childCollections = new ArrayList<>();
    }

    // Method to add a file to this collection
    public void addFile(File file) {
        files.add(file);
        file.addCollection(this); // Also add this collection to the file's collection list
    }

    // Method to add a child collection
    public void addChildCollection(Collection child) {
        childCollections.add(child);
    }

    @Override
    public String toString() {
        return "Collection{name='" + name + "', parent=" + (parent != null ? parent.name : "null") + '}';
    }
}