package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class RemoveLoop {


    public static ListNode removeLoop(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode slow = node;
        ListNode fast = node;


        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }


        if (slow == fast) {

            if (fast == node) {

                while (slow.next != fast) {

                    slow = slow.next;
                }

                slow.next = null;
            } else {

                slow = node;

                while (slow.next != fast.next) {

                    slow = slow.next;
                }

                fast.next = null;
            }
        }


        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);


        root.next.next.next.next = root;

        //  LinkedListUtils.print(root);

        root = removeLoop(root);

        LinkedListUtils.print(root);
    }
}
