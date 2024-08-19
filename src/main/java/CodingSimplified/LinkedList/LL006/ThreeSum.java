package CodingSimplified.LinkedList.LL006;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class ThreeSum {


    public static boolean threeSum(ListNode node1, ListNode node2, ListNode node3, int target) {

        ListNode head2 = node2;
        ListNode head3 = node3;


        while (node1 != null) {


            while (node2 != null && node3 != null) {


                int sum = node1.data + node2.data + node3.data;

                if (sum == target) {

                    return true;
                } else if (sum > target) {

                    node3 = node3.next;
                } else {

                    node2 = node2.next;
                }
            }

            node1 = node1.next;
            node2 = head2;
            node3 = head3;
        }


        return false;

    }


    public static void main(String[] args) {


        ListNode root1 = null;
        root1 = LinkedListUtils.insert(7, root1);
        root1 = LinkedListUtils.insert(3, root1);
        root1 = LinkedListUtils.insert(8, root1);


        ListNode root2 = null;
        root2 = LinkedListUtils.insert(8, root2);
        root2 = LinkedListUtils.insert(10, root2);
        root2 = LinkedListUtils.insert(12, root2);

        ListNode root3 = null;
        root3 = LinkedListUtils.insert(15, root3);
        root3 = LinkedListUtils.insert(11, root3);
        root3 = LinkedListUtils.insert(3, root3);


        boolean threeSumExists = threeSum(root1, root2, root3, 54);

        System.out.println(" three sum exists ? " + threeSumExists);
    }
}
