package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class NodesSum {


    public static int sum(ListNode node) {

        if (node == null) return 0;

        return sum(node.next) + node.data;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        LinkedListUtils.print(root);

        int sum = sum(root);
        System.out.println(" sum is : " + sum);
    }
}
