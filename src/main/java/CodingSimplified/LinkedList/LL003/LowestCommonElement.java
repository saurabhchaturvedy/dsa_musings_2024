package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class LowestCommonElement {


    public static ListNode mergeSort(ListNode node) {

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


        ListNode left = mergeSort(node);
        ListNode right = mergeSort(slow);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;

        ListNode result = null;
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


    public static ListNode getLowestCommonElement(ListNode first, ListNode second) {

        ListNode left = mergeSort(first);
        ListNode right = mergeSort(second);


        return getIntersection(left, right);
    }

    private static ListNode getIntersection(ListNode left, ListNode right) {

        ListNode result = null;


        while (left != null && right != null) {

            if (left.data < right.data) {

                left = left.next;
            } else if (right.data < left.data) {

                right = right.next;
            } else {
                result = left;
                return result;

            }
        }

        return null;
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


        ListNode lowestCommonElement = getLowestCommonElement(root, root2);


        System.out.println(lowestCommonElement.data);
    }
}
