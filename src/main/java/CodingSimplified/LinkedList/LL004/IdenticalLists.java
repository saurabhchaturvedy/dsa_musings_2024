package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class IdenticalLists {


    public static boolean areListIdentical(ListNode first, ListNode second) {

        if (first == null && second == null) return true;

        while (first != null && second != null) {

            first = first.next;
            second = second.next;
        }


        return (first == null) && (second == null);
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(1, root2);
        root2 = LinkedListUtils.insert(2, root2);
        root2 = LinkedListUtils.insert(3, root2);
        root2 = LinkedListUtils.insert(4, root2);
        root2 = LinkedListUtils.insert(5, root2);

        boolean areIdentical = areListIdentical(root, root2);

        System.out.println(" are lists identical : " + areIdentical);
    }
}
