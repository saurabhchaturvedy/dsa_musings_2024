package CodingSimplified.LinkedList;

public class ListNode {


    public int data;
   public ListNode next;
   public ListNode right; // for flattening linked list
    public ListNode random;


    public ListNode(int data) {

        this.data = data;
        this.next = null;
    }
}
