package Atlassian.PostKaratRev1.FileCollection.NestedCollections2;

import java.util.*;

public class FileCollectionService {






    public static Report generateReport(List<File> files,List<Collection> collections)
    {


        Map<String,Long> collectionFileSizeCountMap = new HashMap<>();


        for(File f : files)
        {

            for(String collectionId : f.collectionIds)
            {


                collectionFileSizeCountMap.put(collectionId,collectionFileSizeCountMap.getOrDefault(collectionId,0L)+f.fileSize);
            }
        }

        System.out.println(" test ");

        return null;

    }


    public static void main(String[] args) {


        Set<String> collection1 = new HashSet<>(Arrays.asList("collection1"));
        Set<String> collection2 = new HashSet<>(Arrays.asList("collection2"));
        Set<String> collection3 = new HashSet<>(Arrays.asList("collection1","collection2"));


        File file1 = new File("file1.txt",100,collection1);
        File file2 = new File("file2.txt",200,collection1);
        File file3 = new File("file3.txt",300,collection2);
        File file4 = new File("file4.txt",400,collection3);


        Collection coll1 = new Collection("collection1");
        Collection coll2 = new Collection("collection2");
        Collection coll3 = new Collection("collection3");

        coll3.addCollection("collection1");
        coll3.addCollection("collection2");


        List<File> files = Arrays.asList(file1,file2,file3,file4);
        List<Collection> collections = Arrays.asList(coll1,coll2,coll3);

        Report report = generateReport(files,collections);
    }
}
