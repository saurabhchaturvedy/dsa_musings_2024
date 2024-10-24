package Atlassian.PostKarat20Oct.FileKollection;

public class File {


    String name;

    int size;


    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
