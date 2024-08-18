package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class KthNodeFromEnd {


    public static ListNode kthNodeFromEnd(ListNode node, int k) {

        if (node == null || k < 0) {
            return null;
        }


        ListNode refPtr = node;
        ListNode mainPtr = node;

        int count = 0;


        while (count < k) {

            if (refPtr == null) {

                System.out.println(" not a valid kth node");
                return null;
            }
            count++;
            refPtr = refPtr.next;
        }


        while (refPtr != null) {

            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }


        return mainPtr;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(21, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(47, root);
        root = LinkedListUtils.insert(58, root);

        LinkedListUtils.print(root);


        root = kthNodeFromEnd(root, 2);

        System.out.println(" kth node from end : " + root.data);
    }
}
