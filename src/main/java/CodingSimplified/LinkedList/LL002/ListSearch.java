package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ListSearch {


    public static boolean search(int key, ListNode node) {

        if (node == null) return false;


        ListNode current = node;

        while (current != null) {

            if (current.data == key) return true;

            current = current.next;
        }


        return false;
    }


    public static void main(String[] args) {
        ListNode root = null;

        root= LinkedListUtils.insert(10,root);
        root=LinkedListUtils.insert(20,root);
        root=LinkedListUtils.insert(30,root);
        root=LinkedListUtils.insert(40,root);

        LinkedListUtils.print(root);

        System.out.println(" list search is "+search(30,root));
    }
}
