package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteNodeInLinkedList {


    public static void deleteNode(ListNode node) {

        if (node == null || node.next == null) {
            return;
        }

        node.data = node.next.data;
        node.next = node.next.next;
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


        deleteNode(root.next.next.next);


        LinkedListUtils.print(root);
    }
}
