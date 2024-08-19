package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeAlternatively {


    public static List<ListNode> mergeAlternatively(ListNode node1, ListNode node2) {
        ListNode node1next, node2next;
        ListNode first = node1;
        ListNode second = node2;

        ListNode left = first;
        ListNode right = second;


        while (node1 != null && node2 != null) {


            node1next = node1.next;
            node1.next = node2;
            node1 = node1next;

            node2next = node2.next;
            node2.next = node1next;
            node2 = node2next;

        }

        second = node2;


        return Arrays.asList(first, second);

    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(99, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(39, root);


        LinkedListUtils.print(root);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(5, root2);
        root2 = LinkedListUtils.insert(70, root2);


        List<ListNode> list = mergeAlternatively(root, root2);
        for (ListNode splitList : list) {
            LinkedListUtils.print(splitList);
        }
    }


}
