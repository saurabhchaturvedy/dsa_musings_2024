package DataStructures.SinglyLinkedList;

public class SinglyLinkedList {


    private ListNode head;

    private static class ListNode
    {

        int data;
        ListNode next;

        ListNode(int data)
        {

            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {


        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        singlyLinkedList.head = new ListNode(10);
        ListNode second = new ListNode(20);
        ListNode third = new ListNode(30);
        ListNode fourth = new ListNode(40);
    }
}
