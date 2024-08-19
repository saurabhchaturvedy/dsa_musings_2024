package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class MoveLastNodeToFront {





    public static ListNode moveLastNodeToFront(ListNode node)
    {

        if(node==null && node.next==null)
        {
            return node;
        }


        ListNode current = node;

        while (current.next.next!=null)
        {

            current = current.next;

        }

        ListNode last = current.next;
        last.next = node;
        current.next = null;

        return last;
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

        root = moveLastNodeToFront(root);

        LinkedListUtils.print(root);
    }
}
