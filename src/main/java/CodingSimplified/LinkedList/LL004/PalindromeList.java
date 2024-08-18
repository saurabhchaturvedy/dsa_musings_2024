package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class PalindromeList {


    public static boolean isPalindrome(ListNode head) {

        if (head == null) {
            return false;
        }


        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode reversedSecondHalf = reverse(slow);

        while (reversedSecondHalf != null) {

            if (reversedSecondHalf.data != head.data) {
                return false;
            }

            head = head.next;
            reversedSecondHalf = reversedSecondHalf.next;
        }


        return true;

    }


    public static ListNode reverse(ListNode head) {

        if (head == null) {
            return null;
        }


        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;

        while (current != null) {

            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(1, root);

        LinkedListUtils.print(root);


        boolean isPalindrome = isPalindrome(root);

        System.out.println(" is linked list palindrome ? " + isPalindrome);

    }
}
