package Atlassian.PostKarat20Oct.FileKollection;

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
    }


    public void addFile(File file) {

        this.files.add(file);

        cachedSize = null;
    }


    public void addChildCollection(Collection collection) {

        this.collections.add(collection);
        cachedSize = null;
    }


    public String getName() {
        return name;
    }

    public int getTotalSize() {

        if (cachedSize != null) {
            return cachedSize;
        }


        int totalSize = 0;

        for (File file : files) {

            totalSize += file.getSize();
        }

        for (Collection coll : collections) {

            totalSize += coll.getTotalSize();
        }

        cachedSize = totalSize;

        return totalSize;
    }


}
