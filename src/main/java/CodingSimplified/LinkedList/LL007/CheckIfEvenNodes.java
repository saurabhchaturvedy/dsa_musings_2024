package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CheckIfEvenNodes {


    public static boolean isEvenNodes(ListNode node) {

        if (node == null) {
            return true;
        }


        ListNode current = node;


        while (current.next != null && current.next.next != null) {

            current = current.next.next;
        }


        if (current.next == null) {
            return false;
        } else {

            return true;
        }
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(5, root);


        LinkedListUtils.print(root);


        boolean isEven = isEvenNodes(root);

        System.out.println(" are nodes even in count  ? " + isEven);
    }
}
