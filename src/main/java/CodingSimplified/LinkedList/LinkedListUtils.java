package CodingSimplified.LinkedList;

public class LinkedListUtils {


    static String redColor = "\u001B[31m";
    static String resetColor = "\u001B[0m";

    public static ListNode insert(int data, ListNode node) {

        if (node == null) {
            return new ListNode(data);
        } else {

            node.next = insert(data, node.next);
        }

        return node;
    }


    public static ListNode insertIteratively(int data, ListNode node) {
        if (node == null) {
            return new ListNode(data);
        }

        ListNode head = node;

        while (node.next != null) {
            node = node.next;
        }

        node.next = new ListNode(data);

        return head;
    }


    public static void print(ListNode node) {
        if (node == null) {
            System.out.print(redColor + "NULL" + resetColor);
            System.out.println();
            return;
        }


        System.out.print(node.data + " -> ");
        print(node.next);
    }


    public static void printIteratively(ListNode node) {

        if (node == null) {
            return;
        }


        ListNode current = node;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.print(redColor + "NULL" + resetColor);
    }


    public static ListNode insertAtStart(int data, ListNode node) {

        if (node == null) {
            return new ListNode(data);
        }

        ListNode newNode = new ListNode(data);
        newNode.next = node;
        return newNode;
    }


    public static ListNode insertAtPosition(int data, int position, ListNode node) {

        if (position < 1) {
            System.out.println("Position is invalid");
            return node;
        }

        if (node == null && position > 1) {
            System.out.println(" position is out of range");
            return node;
        }

        if (node == null && position == 1) {

            return new ListNode(data);
        }

        if (position == 1) {

            ListNode newNode = new ListNode(data);
            newNode.next = node;
            return newNode;
        }

        node.next = insertAtPosition(data, position - 1, node.next);
        return node;
    }
}
