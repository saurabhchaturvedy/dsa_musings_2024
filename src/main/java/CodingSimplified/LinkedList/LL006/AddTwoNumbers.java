package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class AddTwoNumbers {


    public static ListNode addTwoNumbers(ListNode first, ListNode second) {


        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int carry = 0;

        while (first != null || second != null) {

            int x = first != null ? first.data : 0;
            int y = second != null ? second.data : 0;

            int sum = (x + y + carry);
            carry = sum / 10;

            tail.next = new ListNode(sum % 10);
            tail = tail.next;


            if (first != null) {
                first = first.next;
            }

            if (second != null) {
                second = second.next;
            }
        }

        if (carry > 0) {

            tail.next = new ListNode(carry);
        }


        return dummy.next;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(3, root);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert(5, root2);
        root2 = LinkedListUtils.insert(6, root2);
        root2 = LinkedListUtils.insert(4, root2);


        ListNode root3 = addTwoNumbers(root, root2);

        LinkedListUtils.print(root3);

    }
}
