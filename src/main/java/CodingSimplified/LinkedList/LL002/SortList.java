package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SortList {


    public static ListNode sortList(ListNode head) {


        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode temp = head;

        while (fast != null && fast.next != null) {

            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        temp.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);

    }


    public static ListNode merge(ListNode first, ListNode second) {

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

        dummyHead.next = (first == null) ? second : first;
        return finalList.next;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(9, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);


        root = sortList(root);
        LinkedListUtils.print(root);
    }
}
