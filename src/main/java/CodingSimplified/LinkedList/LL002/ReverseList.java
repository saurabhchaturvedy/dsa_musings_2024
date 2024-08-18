package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ReverseList {


    public static ListNode reverseRecursive(ListNode node) {

        if (node == null || node.next == null) return node;


        ListNode temp = reverseRecursive(node.next);
        node.next.next = node;
        node.next = null;

        return temp;
    }

    public static ListNode reverseIterative(ListNode node) {

        if (node == null || node.next == null) return node;


        ListNode current = node;
        ListNode next = null;
        ListNode prev = null;

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
        root= LinkedListUtils.insert(1,root);
        root=LinkedListUtils.insert(2,root);
//        root=LinkedListUtils.insert(3,root);
//        root=LinkedListUtils.insert(4,root);
//        root=LinkedListUtils.insert(5,root);

        LinkedListUtils.print(root);

        root = reverseRecursive(root);

        LinkedListUtils.print(root);

        root = reverseIterative(root);

        LinkedListUtils.print(root);

    }
}
