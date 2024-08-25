package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class FlattenListDepthWise {

static ListNode prev=null;
    public static ListNode flattenListDepthWise(ListNode node) {

        if (node == null) {
            return node;
        }


         prev = node;
        ListNode next = node.next;

        if (node.child != null) {
            node.next = flattenListDepthWise(node.child);
        }

        if (next != null) {

            prev.next = flattenListDepthWise(next);
        }

        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(7, root2);
        root2 = LinkedListUtils.insert(8, root2);


        ListNode root3 = null;
        root3 = LinkedListUtils.insert(12, root3);

        ListNode root4 = null;
        root4 = LinkedListUtils.insert(11, root4);

        ListNode root5 = null;
        root5 = LinkedListUtils.insert(13, root5);


        root.next.child = root2;
        root2.next.child = root3;
        root2.child = root4;
        root4.child = root5;


        root = flattenListDepthWise(root);

        LinkedListUtils.print(root);
    }
}
