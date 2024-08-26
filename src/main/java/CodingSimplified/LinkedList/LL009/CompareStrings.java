package CodingSimplified.LinkedList.LL009;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

public class CompareStrings {


    public static int compareStrings(ListNode first, ListNode second) {

        if (first == null && second == null) {
            return 0;
        }


        while (first != null && second != null) {

            if (first.data < second.data) {
                return -1;
            } else {

                if (first.data > second.data) {
                    return 1;
                }
            }

            first = first.next;
            second = second.next;
        }


        if (first == null && second == null) {
            return 0;
        }

        return first != null ? 1 : -1;
    }


    public static void main(String[] args) {


        ListNode root1 = null;
        root1 = LinkedListUtils.insert('e', root1);
        root1 = LinkedListUtils.insert('v', root1);
        root1 = LinkedListUtils.insert('a', root1);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert('e', root2);
        root2 = LinkedListUtils.insert('v', root2);
        root2 = LinkedListUtils.insert('a', root2);


        int x = compareStrings(root1, root2);

        System.out.println(" comparsion result = " + x);
    }
}
