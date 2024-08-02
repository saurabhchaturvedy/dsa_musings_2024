package DataStructures.SinglyLinkedList;

public class SinglyLinkedList {


    private ListNode head;

    private static class ListNode {

        int data;
        ListNode next;

        ListNode(int data) {

            this.data = data;
            this.next = null;
        }
    }


    public void print() {

        ListNode current = head;

        while (current != null) {

            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println(" null ");
    }

    public int length() {
        ListNode current = head;
        int count = 0;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }


    public void addFirst(int data) {
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
    }


    public void addLast(int data) {

        ListNode newNode = new ListNode(data);

        if (head == newNode) {
            head = newNode;
            return;
        }

        ListNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }


    public void addAtPosition(int data, int n) {

        ListNode newNode = new ListNode(data);
        if (n == 1 && head == null) {
            head = newNode;
            return;
        }

        if (n == 1) {
            newNode.next = head;
            head = newNode;
        }

        int count = 0;

        ListNode current = head;

        while (count < n - 1) {
            count++;
            current = current.next;

        }

        ListNode next = current.next;
        current.next = newNode;
        newNode.next = next;
    }

    public static void main(String[] args) {


        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        singlyLinkedList.head = new ListNode(10);
        ListNode second = new ListNode(20);
        ListNode third = new ListNode(30);
        ListNode fourth = new ListNode(40);


        singlyLinkedList.head.next = second;
        second.next = third;
        third.next = fourth;

        singlyLinkedList.print();

        System.out.println(" size of the linked list is : " + singlyLinkedList.length());

        singlyLinkedList.addFirst(60);

        singlyLinkedList.print();

        singlyLinkedList.addLast(80);

        singlyLinkedList.print();

        singlyLinkedList.addAtPosition(45,4);
        singlyLinkedList.addAtPosition(66,1);

        singlyLinkedList.print();
    }
}
