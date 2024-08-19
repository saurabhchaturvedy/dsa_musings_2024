package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteAlternateNodes {


    public static ListNode deleteAlternateNodes(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode current = node;



        while (current != null && current.next!=null) {

            current.next = current.next.next;
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
        root = LinkedListUtils.insert(6, root);

        LinkedListUtils.print(root);


        root = deleteAlternateNodes(root);

        LinkedListUtils.print(root);
    }
}
