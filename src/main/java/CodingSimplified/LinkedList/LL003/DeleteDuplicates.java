package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteDuplicates {


    public static ListNode deleteDuplicatesIterative(ListNode head) {

        if(head==null)
        {
            return head;
        }

        ListNode current = head;

        while(current!=null && current.next!=null)
        {

            if(current.data==current.next.data)
            {

                current.next = current.next.next;
            }
            else
            {

                current = current.next;
            }
        }

        return head;

    }


    public static ListNode deleteDuplicatesRecursive(ListNode head) {

        if(head==null || head.next==null)
        {
            return head;
        }


        if(head.data==head.next.data)
        {

            head.next = head.next.next;
            deleteDuplicatesRecursive(head);
        }
        else
        {
            deleteDuplicatesRecursive(head.next);
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode root = null;
        root = LinkedListUtils.insert(37, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(180, root);


        LinkedListUtils.print(root);


        root = deleteDuplicatesRecursive(root);

        LinkedListUtils.print(root);

    }
}
