package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ListIntersection {


    public static ListNode mergesort(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode slow = node;
        ListNode fast = node;
        ListNode temp = node;

        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        temp.next = null;

        ListNode left = mergesort(node);
        ListNode right = mergesort(slow);

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


    private static ListNode mergeForIntersection(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;


        while (left != null && right != null) {

            if (left.data < right.data) {


                left = left.next;
            } else if (right.data < left.data) {


                right = right.next;
            } else {

                dummy.next = left;
                dummy = dummy.next;
                left = left.next;
                right = right.next;
            }


        }

        dummy.next = null;

        return finalList.next;
    }


    public static ListNode intersection(ListNode first, ListNode second) {

        ListNode left = mergesort(first);
        ListNode right = mergesort(second);

        return mergeForIntersection(left, right);
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(37, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(18, root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(18, root2);
        root2 = LinkedListUtils.insert(14, root2);
        root2 = LinkedListUtils.insert(8, root2);


        ListNode intersection = intersection(root, root2);

        LinkedListUtils.print(intersection);
    }

}
