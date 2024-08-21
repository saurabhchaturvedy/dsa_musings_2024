package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SortAbsoluteList {


    public static ListNode sortAbsoluteList(ListNode node) {

        if (node == null && node.next == null) {
            return node;
        }


        ListNode current = node;
        ListNode prev = null;


        while (current != null) {

            if (current.data < 0) {

                prev.next = current.next;
                current.next = node;
                node = current;
                current = prev.next;

            } else {

                prev = current;
                current = current.next;
            }
        }

        return node;
    }

    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(-2, root);
        root = LinkedListUtils.insert(-3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(-5, root);


        LinkedListUtils.print(root);

        root = sortAbsoluteList(root);

        LinkedListUtils.print(root);
    }
}
