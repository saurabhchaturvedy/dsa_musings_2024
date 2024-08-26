package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class LongestPalindromicString {


    public static int longestPalindromicString(ListNode node) {

        if (node == null) {
            return 0;
        }

        if (node.next == null) {
            return 1;
        }

        ListNode current = node;
        ListNode prev = null;
        ListNode next = null;

        int result = 1;

        while (current != null) {

            next = current.next;
            current.next = prev;

            result = Math.max(result, 2 * commonElementCount(prev, next) + 1);
            result = Math.max(result, 2 * commonElementCount(current, next));

            prev = current;
            current = next;

        }


        return result;
    }

    private static int commonElementCount(ListNode first, ListNode second) {

        int count = 0;

        while (first != null && second != null) {

            if (first.data == second.data) {
                count++;
            }
            else {

                break;
            }

            first = first.next;
            second = second.next;
        }


        return count;
    }


    public static void main(String[] args) {


        ListNode root1 = null;
        root1 = LinkedListUtils.insert('e', root1);
        root1 = LinkedListUtils.insert('v', root1);
        root1 = LinkedListUtils.insert('a', root1);
        root1 = LinkedListUtils.insert('a', root1);
        root1 = LinkedListUtils.insert('v', root1);
        root1 = LinkedListUtils.insert('e', root1);

        LinkedListUtils.print(root1);

        int longestPalindromeLength = longestPalindromicString(root1);

        System.out.println(" length = " + longestPalindromeLength);
    }
}
