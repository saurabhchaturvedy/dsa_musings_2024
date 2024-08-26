package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteLargestElement {


    public static ListNode deleteLargestElement(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode prevLargest = null;
        ListNode largest = node;
        ListNode head = node;
        ListNode prev = node;

        node = node.next;


        while (node != null) {

            if (node.data > largest.data) {

                prevLargest = prev;
                largest = node;
            }

            prev = node;
            node = node.next;
        }


        if (largest == head) {
            head = head.next;
        } else {

            prevLargest.next = largest.next;
        }


        return head;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);


        root = deleteLargestElement(root);

        LinkedListUtils.print(root);

    }
}
