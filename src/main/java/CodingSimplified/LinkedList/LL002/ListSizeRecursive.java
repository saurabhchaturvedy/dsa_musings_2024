package CodingSimplified.LinkedList.LL002;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ListSizeRecursive {


    public static int size(ListNode node) {
        if (node == null) return 0;

        return size(node.next) + 1;
    }


    public static void main(String[] args) {

        ListNode root = null;
        root= LinkedListUtils.insert(10,root);
        root=LinkedListUtils.insert(20,root);
        root=LinkedListUtils.insert(30,root);
        root=LinkedListUtils.insert(40,root);

        LinkedListUtils.print(root);


        System.out.println(" list size is "+size(root));
    }
}
