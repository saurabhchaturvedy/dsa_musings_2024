package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class FlattenLinkedList {


    public static ListNode flatten(ListNode node) {

        if (node == null || node.right == null) {
            return node;
        }


        return merge(node, flatten(node.right));

    }

    private static ListNode merge(ListNode first, ListNode second) {


        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;


        while (first != null && second != null) {

            if (first.data < second.data) {

                dummy.next = first;
                first = first.next;
            } else {

                dummy.next = second;
                second = second.next;
            }

            dummy = dummy.next;
        }


        if (first != null) {

            dummy.next = first;
            first = first.next;
        }

        if (second != null) {

            dummy.next = second;
            second = second.next;
        }


        finalList.next.right = null;
        return finalList.next;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(20, root);
        root = LinkedListUtils.insert(25, root);
        root = LinkedListUtils.insert(65, root);
        root = LinkedListUtils.insert(70, root);


        root.right = LinkedListUtils.insert(7, root.right);
        root.right = LinkedListUtils.insert(12, root.right);
        root.right = LinkedListUtils.insert(23, root.right);
        root.right = LinkedListUtils.insert(24, root.right);


        root.right.right = LinkedListUtils.insert(10, root.right.right);
        root.right.right = LinkedListUtils.insert(15, root.right.right);
        root.right.right = LinkedListUtils.insert(17, root.right.right);
        root.right.right = LinkedListUtils.insert(18, root.right.right);
        root.right.right = LinkedListUtils.insert(21, root.right.right);
        root.right.right = LinkedListUtils.insert(23, root.right.right);


        root.right.right.right = LinkedListUtils.insert(32, root.right.right.right);
        root.right.right.right = LinkedListUtils.insert(39, root.right.right.right);
        root.right.right.right = LinkedListUtils.insert(40, root.right.right.right);


        root = flatten(root);

        LinkedListUtils.print(root);


    }
}
