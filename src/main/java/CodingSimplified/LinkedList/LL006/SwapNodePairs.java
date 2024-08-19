package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class SwapNodePairs {

    public static ListNode swapPairs(ListNode head) {


        if (head == null) {
            return head;
        }


        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode point = dummy;

        while (point.next != null && point.next.next != null) {


            ListNode swap1 = point.next;
            ListNode swap2 = point.next.next;

            swap1.next = swap2.next;
            swap2.next = swap1;

            point.next = swap2;
            point = swap1;
        }


        return dummy.next;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(6, root);

        LinkedListUtils.print(root);

        root = swapPairs(root);

        LinkedListUtils.print(root);
    }
}
