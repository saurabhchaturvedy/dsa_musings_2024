package Atlassian.PostKarat20Oct.FileCollection.FileCollection4;

public class Main {
    public static void main(String[] args) {
        // Creating collections
        Collection collection1 = new Collection("collection1", null); // No parent
        Collection collection2 = new Collection("collection2", collection1); // No parent
        Collection collection3 = new Collection("collection2", null); // No parent
        collection1.parent = collection2; // Set parent for collection1

        // Creating files
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);
        File file3 = new File("file3.txt", 300);
        File file4 = new File("file3.txt", 300);

        // Adding files to collections
        collection1.addFile(file2);
        collection1.addFile(file3);
        collection2.addFile(file4);
        collection3.addFile(file4);

        // Adding collection1 as a child of collection2
        collection2.addChildCollection(collection1);

        // Display the relationships
        System.out.println("Files in " + collection1.name + ": " + collection1.files);
        System.out.println("Files in " + collection2.name + ": " + collection2.files);
        System.out.println("Child collections of " + collection2.name + ": " + collection2.childCollections);
        System.out.println("Parent of " + collection1.name + ": " + collection1.parent.name);
    }
}