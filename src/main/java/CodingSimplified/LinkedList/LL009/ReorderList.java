package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ReorderList {


    public static ListNode reorderList(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        temp.next = null;

        ListNode reversedList = reverse(slow);
        ListNode firstList = head;

        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;

        while (firstList != null || reversedList != null) {

            if (firstList != null) {
                dummy.next = firstList;
                dummy = dummy.next;
                firstList = firstList.next;

            }

            if (reversedList != null) {

                dummy.next = reversedList;
                dummy = dummy.next;
                reversedList = reversedList.next;
            }


        }
        return finalList.next;

    }

    public static ListNode reverse(ListNode head) {

        if (head == null) return null;


        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;

        while (current != null) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);


        root = reorderList(root);

        LinkedListUtils.print(root);
    }
}
