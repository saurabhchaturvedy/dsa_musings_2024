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
            return;
        }


        System.out.print(node.data + " -> ");
        print(node.next);
    }
}
