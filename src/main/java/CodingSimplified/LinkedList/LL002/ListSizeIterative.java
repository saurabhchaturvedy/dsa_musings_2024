package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ListSizeIterative {


    public static int size(ListNode node) {

        if (node == null) return 0;


        ListNode current = node;
        int count = 0;

        while (current != null) {
            count++;
            current = current.next;

        }

        return count;
    }


    public static void main(String[] args) {
        ListNode root = null;

        root= LinkedListUtils.insert(10,root);
        root=LinkedListUtils.insert(20,root);
        root=LinkedListUtils.insert(30,root);
        root=LinkedListUtils.insert(40,root);

        LinkedListUtils.print(root);

        System.out.println(" list size is "+size(root));
    }
}
