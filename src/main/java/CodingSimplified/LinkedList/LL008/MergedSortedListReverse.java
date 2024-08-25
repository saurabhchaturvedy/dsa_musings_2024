package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class MergedSortedListReverse {


    public static ListNode mergeReverse(ListNode node1, ListNode node2) {

        if (node1 == null && node2 == null) {
            return null;
        }


        ListNode next = null;
        ListNode revereList = null;

        while (node1 != null && node2 != null) {

            if (node1.data < node2.data) {


                next = node1.next;
                node1.next = revereList;
                revereList = node1;
                node1 = next;
            } else {

                next = node2.next;
                node2.next = revereList;
                revereList = node2;
                node2 = next;

            }
        }


        ListNode temp = node1 != null ? node1 : node2;

        while (temp != null) {

            next = temp.next;
            temp.next = revereList;
            revereList = temp;
            temp = next;
        }

        return revereList;
    }


    public static void main(String[] args) {


        ListNode root1 = null;
        root1 = LinkedListUtils.insert(1, root1);
        root1 = LinkedListUtils.insert(5, root1);
        root1 = LinkedListUtils.insert(9, root1);
        root1 = LinkedListUtils.insert(10, root1);

        ListNode root2=null;
        root2 = LinkedListUtils.insert(7,root2);
        root2 = LinkedListUtils.insert(8,root2);



        root1 = mergeReverse(root1,root2);

        LinkedListUtils.print(root1);

    }
}
