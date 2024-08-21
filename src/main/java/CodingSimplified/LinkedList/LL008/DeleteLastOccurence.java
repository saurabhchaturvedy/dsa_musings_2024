package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DeleteLastOccurence {





    public static ListNode deleteLastOccurence(ListNode node)
    {
        if(node==null)
        {
            return null;
        }


        Set<Integer> set = new HashSet<>();

        ListNode current = node;

        set.add(current.data);

        while (current!=null && current.next!=null)
        {

            if(!set.contains(current.next.data))
            {

                set.add(current.next.data);
            }
            else {

                current.next = current.next.next;
            }

            current = current.next;
        }


        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(5, root);


        LinkedListUtils.print(root);

        root = deleteLastOccurence(root);

        LinkedListUtils.print(root);
    }
}
