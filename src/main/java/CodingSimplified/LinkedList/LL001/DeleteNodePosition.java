package CodingSimplified.LinkedList.LL001;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteNodePosition {


    public static ListNode deleteNodeAtPosition(int position, ListNode node) {

        if (position < 0) {
            System.out.println("invalid position");
            return node;
        }

        if (node == null && position > 1) {
            System.out.println("invalid position");
            return node;
        }

        if (position == 1) {

            return node.next;
        }


        node.next = deleteNodeAtPosition(position - 1, node.next);
        return node;
    }


    public static void main(String[] args) {

        ListNode root = null;

        root = LinkedListUtils.insert(10, root);
        root = LinkedListUtils.insert(20, root);
        root = LinkedListUtils.insert(30, root);
        root = LinkedListUtils.insert(40, root);
        root = LinkedListUtils.insert(50, root);


        LinkedListUtils.print(root);

        root = deleteNodeAtPosition(4, root);
        LinkedListUtils.print(root);
    }
}
