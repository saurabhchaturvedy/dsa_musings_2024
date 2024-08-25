package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class MoveAllOccurencesToEnd {


    public static ListNode moveAllOccurencesToEnd(ListNode node, int data) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode current = node;

        while (current.next != null) {
            current = current.next;
        }


        ListNode tail = current;
        ListNode temp = tail;
        current = node;

        ListNode prev = null;
        ListNode next = null;
        while (current != temp) {

            if (current.data == data && prev == null) {

                next = current.next;
                tail.next = current;
                tail = tail.next;
                current.next = null;
                current = next;
            } else if (current.data == data && prev != null) {

                next = current.next;
                prev.next = current.next;
                tail.next = current;
                tail = tail.next;
                current.next = null;
                current = next;
            } else {

                prev = current;
                current = current.next;
            }
        }


        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(2, root);


        root = moveAllOccurencesToEnd(root, 2);

        LinkedListUtils.print(root);
    }
}
