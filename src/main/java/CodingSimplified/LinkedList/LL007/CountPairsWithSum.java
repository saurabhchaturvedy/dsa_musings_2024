package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class CountPairsWithSum {


    public static int countPairs(ListNode first, ListNode second, int sum) {

        if (first == null || second == null) {
            return 0;
        }


        Set<Integer> set = new HashSet<>();


        while (first != null) {

            set.add(first.data);
            first = first.next;
        }


        int countPairs = 0;

        while (second != null) {

            if (set.contains(sum - second.data)) {
                countPairs++;

            }

            second = second.next;
        }


        return countPairs;

    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(6, root);
        root = LinkedListUtils.insert(5, root);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert(1, root2);
        root2 = LinkedListUtils.insert(3, root2);
        root2 = LinkedListUtils.insert(4, root2);


        int pairs = countPairs(root, root2, 9);

        System.out.println(" number of pairs are = " + pairs);
    }
}
