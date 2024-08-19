package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ReverseNodesKGroup {


    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k < 0) {
            return null;
        }


        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        int count = k;

        while (current != null && count > 0) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count--;
        }

        if (next != null) {

            if (size(next) >= k) {
                head.next = reverseKGroup(next, k);
            } else {

                head.next = next;
            }

        }

        return prev;
    }

    public static int size(ListNode node) {

        if (node == null) return 0;

        return size(node.next) + 1;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);

        root = reverseKGroup(root, 3);

        LinkedListUtils.print(root);

    }
}
