package CodingSimplified.LinkedList.LL005;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CountOccurence {


    public static int countOccurence(ListNode node, int val) {

        if (node == null) {
            return 0;
        }


        ListNode current = node;

        int count = 0;

        while (current != null) {

            if(current.data==val) {
                count++;
            }
            current = current.next;
        }

        return count;
    }

    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(4, root);

        LinkedListUtils.print(root);


        int count = countOccurence(root,4);
        System.out.println("count is "+count);
    }
}
