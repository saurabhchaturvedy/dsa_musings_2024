package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SwapNodesNonData {


    public static ListNode swapNodesNonData(ListNode node, int firstNodeData, int secondNodeData) {

        if (node == null && node.next == null) {
            return node;
        }


        ListNode current = node;

        while (current != null && current.next != null) {

            if (current.next.data == firstNodeData) {
                break;
            }

            current = current.next;
        }

        ListNode firstNode = current.next;
        ListNode restOfTheLinkedList = firstNode.next;
        current.next = null;

        ListNode temp = restOfTheLinkedList;
        while (temp != null && temp.next != null) {

            if (temp.next.data == secondNodeData) {
                break;

            }

            temp = temp.next;
        }


        ListNode secondNode = temp.next;
        temp.next = firstNode;
        firstNode.next = secondNode.next;


        node.next = secondNode;
        secondNode.next = restOfTheLinkedList;

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

        LinkedListUtils.print(root);


        root = swapNodesNonData(root, 2, 5);

        LinkedListUtils.print(root);
    }
}
