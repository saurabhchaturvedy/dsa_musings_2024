package CodingSimplified.LinkedList.LL001;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CreateLinkedList {


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(15, root);
        root = LinkedListUtils.insert(22, root);
        root = LinkedListUtils.insert(35, root);
        root = LinkedListUtils.insertIteratively(47, root);


        LinkedListUtils.print(root);
        System.out.println();
        //  LinkedListUtils.printIteratively(root);
        root = LinkedListUtils.insertAtStart(80, root);

        LinkedListUtils.print(root);


        root = LinkedListUtils.insertAtPosition(75, 3, root);
        System.out.println();
        LinkedListUtils.print(root);


    }
}
