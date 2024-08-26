package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class LinkedListReverseSubList {


    public static ListNode reverseBetween(ListNode head, int left, int right) {


        if (head == null || head.next == null || left >= right || left < 1 || right < 1) {
            return head;
        }


        ListNode prev = null;
        ListNode current = head;
        int count = 1;

        while (count < left) {
            if (current == null) {

                return head;
            }
            count++;
            prev = current;
            current = current.next;
        }


        ListNode firstPartLastNode = prev;
        ListNode secondPartFirstNode = current;
        count = 0;
        ListNode next = null;

        while (current != null && count <= right - left) {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }


        if (left != 1) {

            firstPartLastNode.next = prev;
        }

        secondPartFirstNode.next = current;


        return (left == 1) ? prev : head;

    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);


        root = reverseBetween(root, 2, 4);

        LinkedListUtils.print(root);
    }
}
