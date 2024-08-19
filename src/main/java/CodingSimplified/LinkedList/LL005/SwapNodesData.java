package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SwapNodesData {


    public static ListNode swapNodesDataWise(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode current = node;


        while (current != null && current.next != null) {

            int temp = current.data;
            current.data = current.next.data;
            current.next.data = temp;

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

        root = swapNodesDataWise(root);

        LinkedListUtils.print(root);
    }
}
