package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class OddEvenList {





    public static ListNode oddEvenList(ListNode head) {

        if(head==null || head.next==null)
        {
            return head;
        }


        ListNode odd = head;
        ListNode even = head.next;

        ListNode evenHead = even;

        while(even!=null && even.next!=null)
        {


            odd.next = odd.next.next;
            odd = odd.next;

            even.next = even.next.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);


        root = oddEvenList(root);

        LinkedListUtils.print(root);

    }
}
