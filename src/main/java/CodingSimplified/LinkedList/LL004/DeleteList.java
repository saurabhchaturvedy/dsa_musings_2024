package CodingSimplified.LinkedList.LL004;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteList {


    public static ListNode delete(ListNode node) {
        return null;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(13, root);
        root = LinkedListUtils.insert(21, root);
        root = LinkedListUtils.insert(33, root);
        root = LinkedListUtils.insert(47, root);
        root = LinkedListUtils.insert(58, root);

        LinkedListUtils.print(root);


        root = delete(root);

        System.out.println(" linked list deleted : " + root);
    }
}
