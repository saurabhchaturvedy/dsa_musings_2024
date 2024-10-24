package Atlassian.PostKarat20Oct.FileKollectionSelf;

import java.util.*;

public class FileCollectionSystem {


    Map<String, File> files;
    Map<String, Collection> collections;
    int totalFileSize;
    TreeSet<Collection> sortedCollections;


    FileCollectionSystem() {

        this.files = new HashMap<>();
        this.collections = new HashMap<>();
        this.totalFileSize = 0;
        this.sortedCollections = new TreeSet<>((a, b) -> {

            int sizeComparison = Integer.compare(b.getTotalSize(), a.getTotalSize());

            if (sizeComparison == 0) {

                return a.getName().compareTo(b.getName());
            }
            return sizeComparison;

        });
    }


    public void addFile(String fileName, int size, List<String> collectionNames) {


        File file = new File(fileName, size);
        this.files.put(fileName, file);
        this.totalFileSize += file.getSize();


        if (collectionNames != null) {

            for (String collection : collectionNames) {

                Collection collection1 = collections.computeIfAbsent(collection, Collection::new);
                collection1.addFile(file);
                updateSortedCollections(collection1);
            }

        }

    }

    private void updateSortedCollections(Collection collection) {


        this.sortedCollections.remove(collection);
        collection.getTotalSize();
        this.sortedCollections.add(collection);
    }


    public void addCollection(String childName, String parentName) {


        Collection child = collections.computeIfAbsent(childName, Collection::new);
        Collection parent = collections.computeIfAbsent(parentName, Collection::new);

        parent.addChildCollection(child);
        updateSortedCollections(parent);


    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public List<String> getTopKCollections(int K) {

        List<String> result = new ArrayList<>();
        int count = 0;

        for (Collection collection : this.sortedCollections) {

            if (count >= K)
                break;

            result.add(collection.getName() + " " + collection.getTotalSize());
            count++;
        }

        return result;
    }


    public static void main(String[] args) {


        FileCollectionSystem fileCollectionSystem = new FileCollectionSystem();


        fileCollectionSystem.addFile("file1.txt", 100, null);
        fileCollectionSystem.addFile("file2.txt", 230, Arrays.asList("c1"));
        fileCollectionSystem.addFile("file3.txt", 230, Arrays.asList("c1", "c2"));
        fileCollectionSystem.addFile("file3.txt", 400, Arrays.asList("c3"));


        fileCollectionSystem.addCollection("c1", "c3");


        System.out.println(fileCollectionSystem.getTotalFileSize());

        System.out.println(fileCollectionSystem.getTopKCollections(2));


        FileCollectionSystem fileCollectionSystem2 = new FileCollectionSystem();

        fileCollectionSystem2.addFile("file1.txt", 100, null);
        fileCollectionSystem2.addFile("file2.txt", 230, Arrays.asList("c1"));
        fileCollectionSystem2.addFile("file3.txt", 230, Arrays.asList("c2"));
        fileCollectionSystem2.addFile("file3.txt", 400, Arrays.asList("c3"));

        System.out.println(fileCollectionSystem2.getTotalFileSize());
        System.out.println(fileCollectionSystem2.getTopKCollections(3));
    }
}
