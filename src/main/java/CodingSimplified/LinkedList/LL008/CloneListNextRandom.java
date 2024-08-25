package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CloneListNextRandom {


    public static ListNode copyRandomList(ListNode head) {

        if (head == null) {
            return null;
        }


        ListNode current = head;

        while (current != null) {

            ListNode newNode = new ListNode(current.data);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }


        LinkedListUtils.print(head);
        current = head;

        while (current != null) {

            if (current.random != null) {

                current.next.random = current.random.next;
            }

            current = current.next.next;
        }


        current = head;
        ListNode newHead = head.next;
        ListNode newCurrent = newHead;


        while (current != null) {

            current.next = newCurrent.next;
            current = current.next;

            if (current != null) {

                newCurrent.next = current.next;
                newCurrent = newCurrent.next;
            }
        }


        return newHead;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(7, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(11, root);
        root = LinkedListUtils.insert(10, root);
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(5, root);


        root.random = root.next.next.next.next.next;
        root.next.random = root;
        root.next.next.random = root.next.next.next.next;
        root.next.next.next.random = root.next.next;
        root.next.next.next.next.random = root;


        root = copyRandomList(root);

        LinkedListUtils.print(root);


    }
}
