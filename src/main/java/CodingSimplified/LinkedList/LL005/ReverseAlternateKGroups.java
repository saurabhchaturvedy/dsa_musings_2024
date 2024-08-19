package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ReverseAlternateKGroups {



    public static ListNode reverseKGroup(ListNode head, int k,boolean canReverse) {

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
                ListNode curr = next;
                if(canReverse) {

                    while (curr != null && k > 0) {
                        curr = curr.next;
                        k--;
                    }
                }
                head.next = reverseKGroup(curr, k,!canReverse);
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
        root = LinkedListUtils.insert(18, root);
        root = LinkedListUtils.insert(28, root);
        root = LinkedListUtils.insert(82, root);
        root = LinkedListUtils.insert(38, root);

        LinkedListUtils.print(root);

        root = reverseKGroup(root, 2,true);

        LinkedListUtils.print(root);
    }
}
