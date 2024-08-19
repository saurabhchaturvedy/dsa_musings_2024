package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteAlternateNodes {


    public static ListNode deleteAlternateNodes(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode current = node;

        boolean flag = true;

        while (current != null) {

            if (flag) current.next = current.next.next;
            flag = !flag;
            current = current.next.next;
        }

        return node;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);


        root = deleteAlternateNodes(root);

        LinkedListUtils.print(root);
    }
}
