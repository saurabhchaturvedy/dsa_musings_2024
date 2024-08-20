package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteEveryKthNode {


    public static ListNode deleteEveryKthNode(ListNode node, int k) {

        if (node == null || k < 0) {
            return null;
        }


        ListNode current = node;
        int count = 0;

        while (current != null && current.next != null) {
            count++;
            if (count == k - 1) {
                ListNode nextNodeAfterDeletedOne = current.next.next;
                current.next = nextNodeAfterDeletedOne;
                current = nextNodeAfterDeletedOne;
                count = 0;
            } else {

                current = current.next;
            }
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
        root = LinkedListUtils.insert(7, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(9, root);


        LinkedListUtils.print(root);


        root = deleteEveryKthNode(root, 4);

        LinkedListUtils.print(root);
    }
}
