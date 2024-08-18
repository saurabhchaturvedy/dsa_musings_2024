package CodingSimplified.LinkedList.LL001;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class InsertElementAtPositionIterative {


    public static ListNode insertAtPositionIterative(int data, int position, ListNode node) {

        if (position < 1) {
            System.out.println("invalid position");
            return node;
        }


        if (node == null && position > 1) {
            System.out.println("position out of range");
            return node;
        }

        if (node == null && position == 1) {

            return new ListNode(data);
        }

        if (position == 1) {

            ListNode newNode = new ListNode(data);
            newNode.next = node;
            return newNode;
        }

        ListNode head = node;
        ListNode previous = null;

        while (node != null && position > 1) {

            previous = node;
            node = node.next;
            position--;
        }

        if (position != 1) {
            System.out.println(" position greater than number of elements");
            return head;
        }

        ListNode newNode = new ListNode(data);
        previous.next = newNode;
        newNode.next = node;

        return head;
    }


    public static void main(String[] args) {


        ListNode root = null;

        root = LinkedListUtils.insert(10,root);
        root = LinkedListUtils.insert(20,root);
        root = LinkedListUtils.insert(30,root);
        root = LinkedListUtils.insert(40,root);
        root = LinkedListUtils.insert(50,root);
        root = LinkedListUtils.insert(60,root);


        LinkedListUtils.print(root);

        root = insertAtPositionIterative(88, 4, root);

        System.out.println();
        LinkedListUtils.print(root);
    }
}
