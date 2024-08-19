package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class MiddleNodeHead {


    public static ListNode middleNodeHead(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }

        ListNode fast = node;
        ListNode slow = node;
        ListNode prev = null;

        while (fast != null && fast.next != null) {

            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }


        prev.next = slow.next;

        slow.next = node;

        return slow;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(99, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(39, root);
        root = LinkedListUtils.insert(79, root);

        LinkedListUtils.print(root);


        root = middleNodeHead(root);

        LinkedListUtils.print(root);
    }
}
