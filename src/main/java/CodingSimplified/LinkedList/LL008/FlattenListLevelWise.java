package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class FlattenListLevelWise {


    public static ListNode flattenListLevelWise(ListNode node) {

        if (node == null) {
            return node;
        }


        ListNode end = node;

        while (end.next != null) {

            end = end.next;
        }


        ListNode current = node;


        while (current != null) {

            if (current.child != null) {

                end.next = current.child;
                end = end.next;

                while (end.next != null) {

                    end = end.next;
                }

            }


            current = current.next;
        }


        return node;
    }


    public static void main(String[] args) {




        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(6, root2);
        root2 = LinkedListUtils.insert(7, root2);
        root2 = LinkedListUtils.insert(8, root2);


        ListNode root3 = null;
        root3 = LinkedListUtils.insert(9, root3);
        root3 = LinkedListUtils.insert(10, root3);


        ListNode root4 = null;
        root4 = LinkedListUtils.insert(11, root4);

        ListNode root5 = null;
        root5 = LinkedListUtils.insert(12, root5);


        root.child = root2;
        root2.next.child = root4;

        root.next.next.next = root3;
        root3.next = root5;



        root = flattenListLevelWise(root);

        LinkedListUtils.print(root);


    }
}
