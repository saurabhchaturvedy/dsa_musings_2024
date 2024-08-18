package CodingSimplified.LinkedList.LL001;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteFrontNode {


    public static ListNode deleteFront(ListNode node) {

        if (node == null) return null;

        return node.next;
    }


    public static void main(String[] args) {

        ListNode root = null;

        root = LinkedListUtils.insert(10, root);
        root = LinkedListUtils.insert(20, root);
        root = LinkedListUtils.insert(30, root);
        root = LinkedListUtils.insert(40, root);
        root = LinkedListUtils.insert(50, root);

        LinkedListUtils.print(root);

        root = deleteFront(root);
        LinkedListUtils.print(root);
    }
}
