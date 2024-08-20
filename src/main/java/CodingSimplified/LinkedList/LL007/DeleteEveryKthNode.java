package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteEveryKthNode {


    public static ListNode deleteEveryKthNode(ListNode node, int k) {


        if (node == null || k <= 1) {
            return null;
        }

        if (node.next == null && k > 1) {
            return node;
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


    public static ListNode deleteEveryKthNode2(ListNode node, int k) {


        if (node == null || k <= 1) {
            return null;
        }

        if (node.next == null && k > 1) {
            return node;
        }


        ListNode current = node;
        int i = 1;

        while (current != null && current.next != null) {

            if (i % (k - 1) == 0) {
                current = current.next.next;
            }

            i++;
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
        root = LinkedListUtils.insert(7, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(9, root);


        LinkedListUtils.print(root);


        root = deleteEveryKthNode(root, 4);

        LinkedListUtils.print(root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(1, root2);
        root2 = LinkedListUtils.insert(2, root2);
        root2 = LinkedListUtils.insert(3, root2);
        root2 = LinkedListUtils.insert(4, root2);
        root2 = LinkedListUtils.insert(5, root2);
        root2 = LinkedListUtils.insert(6, root2);
        root2 = LinkedListUtils.insert(7, root2);
        root2 = LinkedListUtils.insert(8, root2);
        root2 = LinkedListUtils.insert(9, root2);


        root = deleteEveryKthNode(root2,4);

        LinkedListUtils.print(root);


    }
}
