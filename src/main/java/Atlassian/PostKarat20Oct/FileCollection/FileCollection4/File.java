package Atlassian.PostKarat20Oct.FileCollection.FileCollection4;

import java.util.ArrayList;
import java.util.List;

class File {
    String name;
    long size;
    List<Collection> collections; // List of collections this file belongs to

    public File(String name, long size) {
        this.name = name;
        this.size = size;
        this.collections = new ArrayList<>();
    }

    // Method to add a collection to the file
    public void addCollection(Collection collection) {
        collections.add(collection);
    }

    @Override
    public String toString() {
        return "File{name='" + name + "', size=" + size + '}';
    }
}