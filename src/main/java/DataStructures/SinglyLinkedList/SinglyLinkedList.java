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


    public ListNode deleteFirst() {

        if (head == null) {
            return null;
        }

        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return temp;
    }


    public ListNode deleteLast() {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode previous = null;
        ListNode current = head;

        while (current.next != null) {

            previous = current;
            current = current.next;
        }


        previous.next = null;
        return current;
    }


    public void deleteNodeAtPosition(int pos) {


        if (pos == 1) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;

        }


        ListNode previous = head;

        int count = 0;

        while (count < pos - 2) {
            count++;
            previous = previous.next;

        }


        ListNode current = previous.next;
        previous.next = current.next;
        current.next = null;

    }


    public boolean search(int key) {

        if (head == null) {
            return false;
        }

        ListNode current = head;

        while (current != null) {

            if (current.data == key) {
                return true;
            }

            current = current.next;
        }

        return false;
    }


    public ListNode reverse() {

        if (head == null) {
            return null;
        }

        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;


        while (current != null) {

            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }


        return previous;
    }


    public ListNode getMiddleNode() {

        if (head == null) {
            return null;
        }

        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {

            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        return slowPtr;
    }


    public ListNode getNthNodeFromEnd(int n) {

        if (head == null) {
            return null;
        }

        ListNode mainPtr = head;
        ListNode refPtr = head;

        int count = 0;

        while (count < n) {

            refPtr = refPtr.next;
            count++;
        }

        while (refPtr != null) {

            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }

        return mainPtr;
    }


    public void removeDuplicatesFromSortedList() {

        if (head == null) {
            return;
        }


        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.data == current.next.data) {

                current.next = current.next.next;
            }

            current = current.next;
        }
    }


    public ListNode insertNodeInASortedList(int data) {

        ListNode newNode = new ListNode(data);

        if (head == null) {
            return new ListNode(data);
        }


        ListNode current = head;
        ListNode previous = null;


        while (current != null && current.data < newNode.data) {

            previous = current;
            current = current.next;
        }


        newNode.next = current;
        previous.next = newNode;
        return head;
    }


    public void deleteKey(int key) {

        ListNode current = head;
        ListNode previous = null;

        if (head.data == key) {
            head = head.next;
            return;
        }


        while (current != null && current.data != key) {

            previous = current;
            current = current.next;
        }


        if (current == null) return;

        previous.next = current.next;
    }


    public boolean hasLoop() {

        ListNode fastPtr = head;
        ListNode slowPtr = head;


        while (fastPtr != null && fastPtr.next != null) {

            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if (fastPtr == slowPtr) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {


        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        singlyLinkedList.head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode six = new ListNode(6);


        singlyLinkedList.head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = third;

       // singlyLinkedList.print();

        System.out.println(" size of the linked list is : " + singlyLinkedList.length());



        boolean search = singlyLinkedList.search(30);

        System.out.println(" Element found ? " + search);


        //    ListNode reverse = singlyLinkedList.reverse();

        //    singlyLinkedList.head = reverse;

     //   singlyLinkedList.print();


        ListNode middleNode = singlyLinkedList.getMiddleNode();

        System.out.println(" middle node is " + middleNode.data);


        ListNode nthNodeFromEnd = singlyLinkedList.getNthNodeFromEnd(2);

        System.out.println(" Nth node from the end " + nthNodeFromEnd.data);


     //   singlyLinkedList.print();

        singlyLinkedList.removeDuplicatesFromSortedList();

     //   singlyLinkedList.print();

        ListNode listNode = singlyLinkedList.insertNodeInASortedList(50);

        singlyLinkedList.head = listNode;

     //   singlyLinkedList.print();


        // singlyLinkedList.deleteKey(40);

       singlyLinkedList.print();

        boolean hasLoop = singlyLinkedList.hasLoop();

        System.out.println(" linked list has loop ? " + hasLoop);
    }
}
