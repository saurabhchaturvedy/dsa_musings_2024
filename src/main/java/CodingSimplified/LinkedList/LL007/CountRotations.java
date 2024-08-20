package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CountRotations {


    public static int countRotations(ListNode node) {

        if (node == null || node.next == null) {
            return 0;
        }


        int count = 0;


        while (node.next != null) {

            if (node.data < node.next.data) {

                count++;
            } else {

                break;
            }

            node = node.next;
        }


        if (node.next == null) {
            return 0;
        } else {

            return count + 1;
        }
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(12, root);
        root = LinkedListUtils.insert(14, root);
        root = LinkedListUtils.insert(3, root);
        root = LinkedListUtils.insert(8, root);
        root = LinkedListUtils.insert(5, root);


        int rotations = countRotations(root);

        System.out.println(" number of rotations are : " + rotations);


    }
}
