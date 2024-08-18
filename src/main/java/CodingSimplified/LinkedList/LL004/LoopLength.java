package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class LoopLength {


    public static int loopLength(ListNode node) {

        if (node == null) {
            return 0;
        }


        ListNode slow = node;
        ListNode fast = node;

        int length = 0;


        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }


        if (slow == fast) {

            while (slow.next != fast) {

                length++;
                slow = slow.next;
            }
        }

        return length;
    }

    public static void main(String[] args) {
        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);


        root.next.next.next.next = root;

        //  LinkedListUtils.print(root);

        int length = loopLength(root);

        System.out.println(" loop exists ? : " + length);
    }
}
