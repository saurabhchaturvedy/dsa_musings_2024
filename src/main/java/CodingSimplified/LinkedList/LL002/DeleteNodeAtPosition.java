package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteNodeAtPosition {






    public static ListNode deleteAtPosition(int position,ListNode node)
    {

        if(position<1)
        {
            System.out.println("invalid position ");
            return node;
        }


        if(node==null && position>=1)
        {
            System.out.println("invalid position");
            return node;
        }


        if(position==1)
        {
            return node.next;
        }


        ListNode head = node;
        ListNode prev = null;

        while (node!=null && position>1)
        {

            prev = node;
            node = node.next;
            position--;
        }


        if(position!=1)
        {
            System.out.println(" position is greater than number of nodes");
            return head;
        }


        prev.next = node.next;
        return head;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root= LinkedListUtils.insert(10,root);
        root=LinkedListUtils.insert(20,root);
        root=LinkedListUtils.insert(30,root);
        root=LinkedListUtils.insert(40,root);

        LinkedListUtils.print(root);

        root = deleteAtPosition(3,root);
        LinkedListUtils.print(root);
    }
}
