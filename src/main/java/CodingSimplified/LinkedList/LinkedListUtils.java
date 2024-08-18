package CodingSimplified.LinkedList;

public class LinkedListUtils {


    public static ListNode insert(int data, ListNode node) {

        if (node == null) {
            return new ListNode(data);
        } else {

            node.next = insert(data, node.next);
        }

        return node;
    }


    public static void print(ListNode node) {
        if (node == null) {
            System.out.print("Null");
            return;
        }


        System.out.print(node.data + " -> ");
        print(node.next);
    }
}
