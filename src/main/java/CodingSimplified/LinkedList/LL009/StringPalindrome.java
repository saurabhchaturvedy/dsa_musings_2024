package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class StringPalindrome {


    public static boolean isPalindrome(ListNode node) {

        if (node == null || node.next == null) {
            return true;
        }


        ListNode slow = node;
        ListNode fast = node;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode firstList = node;
        ListNode secondList = reverse(slow);


        while (secondList != null) {

            if (firstList.data != secondList.data) {
                return false;
            }

            firstList = firstList.next;
            secondList = secondList.next;
        }


        return true;
    }

    private static ListNode reverse(ListNode head) {


        if (head == null) {
            return null;
        }


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
        root = LinkedListUtils.insert('r', root);
        root = LinkedListUtils.insert('a', root);
        root = LinkedListUtils.insert('d', root);
        root = LinkedListUtils.insert('a', root);
        root = LinkedListUtils.insert('r', root);

        LinkedListUtils.print(root);


        boolean isPalindrome = isPalindrome(root);

        System.out.println(" is string palindrome ? " + isPalindrome);
    }
}
