package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SplitAlternatively {


    public static List<ListNode> splitListAlternatively(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode firstNode = node;
        ListNode firstTemp = node;
        ListNode secondNode = node.next;
        ListNode secondTemp = node.next;
        node = node.next.next;
        while (node != null && node.next != null) {

            firstTemp.next = node;
            secondTemp.next = node.next;

            firstTemp = firstTemp.next;
            secondTemp = secondTemp.next;

            node = node.next.next;
        }


        if (node != null) {

            firstTemp.next = node;
            firstTemp = firstTemp.next;
        }


        firstTemp.next = null;
        secondTemp.next = null;

        return Arrays.asList(firstNode, secondNode);
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);

        List<ListNode> splitLists = splitListAlternatively(root);


        System.out.println(splitLists.size());
        for (ListNode splitList : splitLists) {
            LinkedListUtils.print(splitList);
        }
    }
}
