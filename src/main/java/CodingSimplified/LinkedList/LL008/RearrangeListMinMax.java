package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class RearrangeListMinMax {


    public static ListNode sortList(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode temp = node;
        ListNode slow = node;
        ListNode fast = node;


        while (fast != null && fast.next != null) {

            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }


        temp.next = null;

        ListNode left = sortList(node);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;

        while (left != null && right != null) {

            if (left.data < right.data) {

                dummy.next = left;
                left = left.next;
            } else {

                dummy.next = right;
                right = right.next;
            }

            dummy = dummy.next;
        }


        if (left != null) {

            dummy.next = left;
            left = left.next;
        }

        if (right != null) {

            dummy.next = right;
            right = right.next;
        }


        return finalList.next;
    }


    public static ListNode rearrangeMinMax(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode sortedList = sortList(node);

        LinkedListUtils.print(sortedList);
        ListNode slow = sortedList;
        ListNode fast = sortedList;
        ListNode temp = sortedList;


        while (fast != null && fast.next != null) {

            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        temp.next = null;

        LinkedListUtils.print(slow);

        ListNode reversedList = reverse(slow);
        ListNode firstList = sortedList;

        LinkedListUtils.print(firstList);


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
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(6, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(4, root);


        root = rearrangeMinMax(root);

        LinkedListUtils.print(root);
    }
}
