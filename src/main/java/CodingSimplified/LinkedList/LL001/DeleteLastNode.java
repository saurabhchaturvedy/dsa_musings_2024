package CodingSimplified.LinkedList.LL001;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteLastNode {


    public static ListNode deleteLast(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        ListNode head = node;

        while (node.next.next != null) {

            node = node.next.next;
        }


        node.next = null;
        return head;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root=LinkedListUtils.insert(10,root);
        root=LinkedListUtils.insert(20,root);
        root=LinkedListUtils.insert(30,root);
        root=LinkedListUtils.insert(40,root);


        LinkedListUtils.print(root);


        root = deleteLast(root);
        LinkedListUtils.print(root);
    }
}
