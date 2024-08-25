package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class PartitionList {



    public static ListNode partition(ListNode head, int x) {

        if(head==null || head.next==null)
        {
            return head;
        }

        ListNode small = new ListNode(-1);
        ListNode higher = new ListNode(-2);

        ListNode smallerSection = small;
        ListNode higherSection = higher;

        while(head!=null)
        {

            if(head.data<x)
            {

                smallerSection.next = head;
                smallerSection = smallerSection.next;

            }
            else
            {
                higherSection.next = head;
                higherSection = higherSection.next;

            }

            head = head.next;
        }

        higherSection.next=null;
        smallerSection.next = small.next;
        return small.next;

    }

    public static void main(String[] args) {

        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(2, root);


        root = partition(root,3);

        LinkedListUtils.print(root);
    }
}
