package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class KthNode {


    public static ListNode kthNode(ListNode node, int k) {

        if (node == null) {
            return node;
        }


        ListNode current = node;
        int count = 0;
        while (current != null && current.next != null && count < k - 1) {

            count++;
            current = current.next;

        }


        return current;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(21, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(47, root);
        root = LinkedListUtils.insert(58, root);


        LinkedListUtils.print(root);

        root = kthNode(root, 3);

        System.out.println(" kth element is = " + root.data);
    }
}
