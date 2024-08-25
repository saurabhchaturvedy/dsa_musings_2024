package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class NextHigherValueRandom {


    public static ListNode nextHigherValue(ListNode node) {

        if (node == null || node.next == null) return node;


        ListNode current = node;

        while (current != null) {
            current.random = current.next;
            current = current.next;
        }

        node = sortList(node);

        return node;
    }

    private static ListNode sortList(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode temp = node;
        ListNode slow = node;
        ListNode fast = node;

        while (fast != null && fast.random != null) {

            temp = slow;
            slow = slow.random;
            fast = fast.random.random;
        }

        temp.random = null;


        ListNode left = sortList(node);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;

        while (left != null && right != null) {

            if (left.data < right.data) {

                dummy.random = left;
                left = left.random;
            } else {

                dummy.random = right;
                right = right.random;
            }

            dummy = dummy.random;
        }


        if (left != null) {

            dummy.random = left;
            left = left.random;
        }

        if (right != null) {

            dummy.random = right;
            right = right.random;
        }


        return finalList.random;
    }


    public static void printRandom(ListNode node)
    {

        if(node==null)
            return;

        System.out.print(node.data+" ");
        printRandom(node.random);
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(10, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);

        LinkedListUtils.print(root);


        root = nextHigherValue(root);


        printRandom(root);


    }
}
