package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class Sort012 {


    public static ListNode sort012(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }

        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;

        ListNode head = node;
        while (node != null) {

            if (node.data == 0) {
                count_0++;
            } else if (node.data == 1) {
                count_1++;
            } else {

                count_2++;
            }

            node = node.next;
        }

        node = head;

        while (count_0 > 0) {
            node.data = 0;
            node = node.next;
            count_0--;

        }

        while (count_1 > 0) {
            node.data = 1;
            node = node.next;
            count_1--;

        }


        while (count_2 > 0) {
            node.data = 2;
            node = node.next;
            count_2--;

        }


        return head;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(0, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(1, root);


        LinkedListUtils.print(root);


        root = sort012(root);

        LinkedListUtils.print(root);
    }
}
