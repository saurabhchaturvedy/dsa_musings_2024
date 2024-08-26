package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LargestSmallestElement {


    public static Map<String, Integer> largestSmallestElement(ListNode node) {

        if (node == null) {
            return new HashMap<>();
        }


        Map<String, Integer> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        while (node != null) {


            if (node.data > max) {
                max = node.data;
            }

            if (node.data < min) {

                min = node.data;
            }

            node = node.next;
        }


        map.put("MIN", min);
        map.put("MAX", max);

        return map;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);


        Map<String, Integer> map = largestSmallestElement(root);

        System.out.println(" min - max " + map);

    }
}
