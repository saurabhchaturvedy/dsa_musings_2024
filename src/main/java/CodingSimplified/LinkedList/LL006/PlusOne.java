package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LL005.ReverseIterative;
import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class PlusOne {


    public static ListNode plusOne(ListNode node) {

        if (node == null) {
            return null;
        }

        ListNode reversedList = ReverseIterative.reverse(node);
        ListNode current = reversedList;
        ListNode resultNode = null;
        int carry = 1;


        while (current != null) {

            int temp = current.data + carry;

            current.data = temp % 10;
            carry = temp / 10;
            resultNode = current;
            current = current.next;
        }


        if (carry != 0) {

            resultNode.next = new ListNode(1);
        }


        return ReverseIterative.reverse(reversedList);
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(9, root);
        root = LinkedListUtils.insert(9, root);
        root = LinkedListUtils.insert(9, root);


        LinkedListUtils.print(root);

        root = plusOne(root);

        LinkedListUtils.print(root);
    }
}
