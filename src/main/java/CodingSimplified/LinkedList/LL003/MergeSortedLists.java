package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class MergeSortedLists {

    public static ListNode mergeTwoLists(ListNode first, ListNode second) {


        ListNode dummyHead = new ListNode(-1);
        ListNode finalList = dummyHead;

        while (first != null && second != null) {

            if (first.data < second.data) {

                dummyHead.next = first;
                first = first.next;
            } else {

                dummyHead.next = second;
                second = second.next;
            }

            dummyHead = dummyHead.next;
        }

        if (first != null) {
            dummyHead.next = first;
            first = first.next;

        }

        if (second != null) {
            dummyHead.next = second;
            second = second.next;

        }

        return finalList.next;

    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(21, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(47, root);
        root = LinkedListUtils.insert(58, root);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert(10, root2);
        root2 = LinkedListUtils.insert(20, root2);
        root2 = LinkedListUtils.insert(30, root2);
        root2 = LinkedListUtils.insert(40, root2);
        root2 = LinkedListUtils.insert(50, root2);


        ListNode root3 = mergeTwoLists(root, root2);

        LinkedListUtils.print(root3);


    }
}
