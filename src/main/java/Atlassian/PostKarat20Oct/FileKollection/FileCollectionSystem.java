package Atlassian.PostKarat20Oct.FileKollection;

import java.util.*;

public class FileCollectionSystem {


    Map<String, File> files;
    Map<String, Collection> collections;
    int totalFileSize = 0;
    TreeSet<Collection> sortedCollections;


    FileCollectionSystem() {

        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.totalFileSize = 0;
        this.sortedCollections = new TreeSet<>((a, b) -> {

            Integer sizeCompare = Integer.compare(b.getTotalSize(), a.getTotalSize());

            if (sizeCompare == 0) {

                return a.getName().compareTo(b.getName());
            }

            return sizeCompare;

        });

    }


    public void addFile(String fileName, int size, List<String> collectionList) {


        File file = new File(fileName, size);
        files.put(fileName, file);
        totalFileSize += file.getSize();
        if (collectionList != null) {

        for (String coll : collectionList) {



                Collection c = collections.computeIfAbsent(coll, k -> new Collection(k));
                c.addFile(file);
                updateSortedCollections(c);
            }
        }

    }


    public void addCollection(String childName, String parentName) {

        Collection child = collections.computeIfAbsent(childName, k -> new Collection(k));
        Collection parent = collections.computeIfAbsent(parentName, k -> new Collection(k));

        parent.addChildCollection(child);
        updateSortedCollections(parent);
    }

    private void updateSortedCollections(Collection c) {


        sortedCollections.remove(c);
        c.getTotalSize();
        sortedCollections.add(c);
    }


    public List<String> getTopK(int topK) {

        List<String> topkk = new ArrayList<>();
        int count = 0;

        for (Collection c : sortedCollections) {

            if (count >= topK)
                break;
                topkk.add(c.getName());
            count++;
        }

        return topkk;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public static void main(String[] args) {

    }

}
