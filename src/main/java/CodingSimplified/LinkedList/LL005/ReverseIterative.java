package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ReverseIterative {


    public static ListNode reverse(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode current = node;
        ListNode prev = null;
        ListNode next = null;

        while (current != null) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }


    public static void main(String[] args) {



        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);

        root = reverse(root);

        LinkedListUtils.print(root);
    }
}
