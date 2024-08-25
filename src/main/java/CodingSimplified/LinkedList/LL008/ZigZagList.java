package CodingSimplified.LinkedList.LL008;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ZigZagList {


    public static ListNode zigZagList(ListNode node) {

        if (node == null) {
            return null;
        }


        ListNode current = node;
        boolean flag = true;


        while (current.next != null) {


            if (flag) {

                if (current.data > current.next.data) {

                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                flag = !flag;
            } else {


                if (current.data < current.next.data) {

                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }

                flag = !flag;
            }


            current = current.next;
        }

        return node;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(2, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(5, root);
        root = LinkedListUtils.insert(9, root);
        root = LinkedListUtils.insert(11, root);
        root = LinkedListUtils.insert(12, root);


        root = zigZagList(root);

        LinkedListUtils.print(root);
    }
}
