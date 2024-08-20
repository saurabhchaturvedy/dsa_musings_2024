package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class InsertIntoMiddle {


    public static ListNode insertIntoMiddle(ListNode node, int data) {

        if (node == null) {
            return null;
        }


        ListNode slow = node;
        ListNode fast = node;


        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode newNode = new ListNode(data);
        newNode.next = slow.next;
        slow.next = newNode;

        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(5, root);


        root = insertIntoMiddle(root, 34);

        LinkedListUtils.print(root);
    }
}
