package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CheckLoop {


    public static boolean hasLoop(ListNode node) {

        if (node == null) {
            return false;
        }


        ListNode slow = node;
        ListNode fast = node;


        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }


        return false;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);


        root.next.next.next.next = root;

      //  LinkedListUtils.print(root);

        boolean loopExists = hasLoop(root);

        System.out.println(" loop exists ? : " + loopExists);
    }


}
