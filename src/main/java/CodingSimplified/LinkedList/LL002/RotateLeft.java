package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class RotateLeft {


    public static ListNode rotateLeft(ListNode node, int k) {

        if (node == null || k < 0) return node;

        int size = ListSizeRecursive.size(node);
        k = k % size;

        if (k == 0) {
            return node;
        }

        ListNode partToRotate = node;
        int i = 1;

        while (i < k) {
            partToRotate = partToRotate.next;
            i++;
        }


        ListNode newStartHead = partToRotate.next;
        ListNode finalHead = newStartHead;
        partToRotate.next = null;

        while (newStartHead.next != null) {

            newStartHead = newStartHead.next;
        }

        newStartHead.next = node;
        return finalHead;
    }

    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);

        root = rotateLeft(root, 2);

        LinkedListUtils.print(root);
    }
}
