package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class FirstHalfSecondHalfMatching {


    public static boolean bothHalvesMatching(ListNode node) {

        if (node == null || node.next == null) {
            return true;
        }


        ListNode slow = node;
        ListNode fast = node;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode secondHalf = slow.next;

        while (secondHalf != null) {

            if (node.data != secondHalf.data) {
                return false;
            }

            node = node.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);

        LinkedListUtils.print(root);


        boolean areHalvesMatching = bothHalvesMatching(root);

        System.out.println(" are halves matching ? " + areHalvesMatching);
    }
}
