package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class UnionLinkedLists {


    public static ListNode union(ListNode first, ListNode second) {

        if (first == null || second == null) return first == null ? second : first;

        if (first == null && second == null) return null;


        ListNode dummy = new ListNode(-1);
        ListNode result = dummy;

        Set<Integer> set = new HashSet<>();

        while (first != null) {
            if (!set.contains(first.data)) {

                result.next = first;
                result = result.next;
                set.add(first.data);
            }

            first = first.next;
        }


        while (second != null) {
            if (!set.contains(second.data)) {

                result.next = second;
                result = result.next;
                set.add(second.data);
            }

            second = second.next;
        }

        result.next = null;
        return dummy.next;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert(1, root2);
        root2 = LinkedListUtils.insert(3, root2);
        root2 = LinkedListUtils.insert(4, root2);


        root = union(root, root2);

        LinkedListUtils.print(root);
    }
}
