package CodingSimplified.LinkedList.LL003;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class DeleteNNodesAfterMNodes {


    public static ListNode deleteNNodesAfterMNodes(int m, int n, ListNode node) {

        if (node == null) {
            return node;
        }


        ListNode temp1 = node;
        ListNode temp2 = node;

        int i = 0;
        int j = 0;

        while (i++ < m - 1) {

            temp1 = temp1.next;
        }


        temp2 = (m == 0) ? temp1 : temp1.next;


        while (j++ < n) {

            if (temp2 == null) {

                System.out.println(" list does not have enough nodes");
                return null;
            }

            temp2 = temp2.next;
        }


        if (m == 0) {

            return temp2;
        } else {


            temp1.next = temp2;
        }


        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(37, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(80, root);
        root = LinkedListUtils.insert(180, root);
        root = LinkedListUtils.insert(200, root);
        root = LinkedListUtils.insert(220, root);


        LinkedListUtils.print(root);


        root = deleteNNodesAfterMNodes(2,3,root);

        LinkedListUtils.print(root);
    }
}
