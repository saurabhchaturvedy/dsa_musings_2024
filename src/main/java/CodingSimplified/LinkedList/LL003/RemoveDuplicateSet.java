package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateSet {


    public static ListNode removeDuplicate(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }


        Set<Integer> set = new HashSet<>();


        ListNode current = node;
        ListNode prev = null;

        while (current != null) {

            if (!set.contains(current.data)) {

                set.add(current.data);
                prev = current;
                current = current.next;
            } else {

                prev.next = current.next;
                current = current.next;
            }
        }

        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(37, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(180, root);


        LinkedListUtils.print(root);


        root = removeDuplicate(root);

        LinkedListUtils.print(root);
    }
}
