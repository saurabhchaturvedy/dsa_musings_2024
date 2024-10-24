package Atlassian.PostKarat20Oct.FileKollectionSelf;

import java.util.ArrayList;
import java.util.List;

public class Collection {


    String name;

    List<File> files;
    List<Collection> collections;
    Integer cachedSize;


    Collection(String name) {

        this.name = name;
        this.files = new ArrayList<>();
        this.collections = new ArrayList<>();
        this.cachedSize = null;
    }


    public void addFile(File file) {

        this.files.add(file);
        cachedSize = null;

    }


    public void addChildCollection(Collection collection) {

        this.collections.add(collection);
    }


    public String getName() {
        return name;
    }


    public int getTotalSize() {

        int totalSize = 0;

        for (File file : this.files) {

            totalSize += file.getSize();
        }


        for (Collection c : collections) {

            totalSize += c.getTotalSize();
        }

        return totalSize;
    }
}
