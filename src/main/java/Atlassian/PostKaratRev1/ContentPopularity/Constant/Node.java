package Atlassian.PostKaratRev1.ContentPopularity.Constant;

public class Node {


    int contentId;

    Node prev;
    Node next;

    Node(int contentId) {

        this.contentId = contentId;
    }
}
