package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteMiddleNode {


    public static ListNode deleteMiddleNode(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode slow = node;
        ListNode fast = node;
        ListNode prev = null;


        while (fast != null && fast.next != null) {

            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;

        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(21, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(47, root);
        root = LinkedListUtils.insert(58, root);

        LinkedListUtils.print(root);

        root = deleteMiddleNode(root);

        LinkedListUtils.print(root);
    }
}
