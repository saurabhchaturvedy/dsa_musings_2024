package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SeparateOddEven {


    public static ListNode separateEvenOdd(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode evenStart = null;
        ListNode evenEnd = null;
        ListNode oddStart = null;
        ListNode oddEnd = null;


        while (node != null) {

            if (node.data % 2 == 0) {

                if (evenStart == null) {
                    evenStart = node;
                    evenEnd = node;
                } else {


                    evenEnd.next = node;
                    evenEnd = evenEnd.next;
                }
            } else {

                if (oddStart == null) {
                    oddStart = node;
                    oddEnd = node;
                } else {


                    oddEnd.next = node;
                    oddEnd = oddEnd.next;
                }

            }

            node = node.next;
        }

        evenEnd.next = oddStart;
        oddEnd.next = null;

        return evenStart;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);

        root = separateEvenOdd(root);

        LinkedListUtils.print(root);
    }
}
