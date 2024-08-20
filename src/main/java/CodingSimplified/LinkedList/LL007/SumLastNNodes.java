package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SumLastNNodes {


    public static int sumLastNNodes(ListNode node, int n) {

        if (node == null || n < 0) {
            return -1;
        }


        ListNode mainPtr = node;
        ListNode refPtr = node;

        int count = 0;

        while (count < n) {
            count++;
            refPtr = refPtr.next;
        }


        while (refPtr != null) {

            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }


        ListNode current = mainPtr;
        int sum = 0;

        while (current != null) {

            sum += current.data;
            current = current.next;
        }


        return sum;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(15, root);
        root = LinkedListUtils.insert(11, root);
        root = LinkedListUtils.insert(7, root);
        root = LinkedListUtils.insert(21, root);

        LinkedListUtils.print(root);


        int sum = sumLastNNodes(root, 3);

        System.out.println(" last N node sum is = " + sum);
    }
}
