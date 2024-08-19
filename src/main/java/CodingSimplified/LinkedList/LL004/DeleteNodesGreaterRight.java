package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteNodesGreaterRight {




    public static ListNode deleteNodesGreaterOnRight(ListNode node)
    {

        if(node==null)
        {
            return null;
        }

        ListNode current = node;
        ListNode prev = null;
        ListNode next = null;


        while (current!=null)
        {

            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }


        current = prev.next;
        prev.next = null;

        while (current!=null)
        {

            ListNode next2 = current.next;
            if(current.data>= prev.data)
            {
                current.next = prev;
                prev = current;
            }

            current = next2;
        }

        return prev;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);

        LinkedListUtils.print(root);

        root = deleteNodesGreaterOnRight(root);

        LinkedListUtils.print(root);
    }
}
