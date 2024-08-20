package CodingSimplified.LinkedList.LL007;

import CodingSimplified.LinkedList.LinkedListUtils;
import CodingSimplified.LinkedList.ListNode;

import java.util.*;

public class MergeKSortedLists {


    public static ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        return mergeKLists(lists, 0, lists.length - 1);

    }


    public static ListNode mergeKLists(ListNode[] lists, int start, int end) {


        if (start == end) {

            return lists[start];
        }


        int mid = (start + (end - start) / 2);

        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid + 1, end);

        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {


        ListNode dummy = new ListNode(-1);
        ListNode finalList = dummy;


        while (left != null && right != null) {

            if (left.data < right.data) {

                dummy.next = left;
                left = left.next;
            } else {

                dummy.next = right;
                right = right.next;
            }

            dummy = dummy.next;
        }


        while (left != null) {

            dummy.next = left;
            left = left.next;
            dummy = dummy.next;
        }

        while (right != null) {

            dummy.next = right;
            right = right.next;
            dummy = dummy.next;
        }


        return finalList.next;
    }


    public static ListNode mergeKSortedListsHeap(ListNode[] lists) {


        Queue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);


        for (ListNode node : lists) {

            if (node == null) continue;

            minHeap.add(node);
        }


        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;


        while (!minHeap.isEmpty()) {

            ListNode top = minHeap.poll();

            curr.next = top;
            curr = curr.next;

            if (top.next != null) {

                minHeap.add(top.next);
            }
        }

        return dummy.next;
    }


    public static void main(String[] args) {


        ListNode root = null;
        root = LinkedListUtils.insert(1, root);
        root = LinkedListUtils.insert(4, root);
        root = LinkedListUtils.insert(5, root);

        ListNode root2 = null;
        root2 = LinkedListUtils.insert(1, root2);
        root2 = LinkedListUtils.insert(3, root2);
        root2 = LinkedListUtils.insert(4, root2);

        ListNode root3 = null;
        root3 = LinkedListUtils.insert(2, root3);
        root3 = LinkedListUtils.insert(6, root3);


        ListNode[] lists = {root, root2, root3};
        //  root = mergeKLists(lists);

        LinkedListUtils.print(root);


        root = mergeKSortedListsHeap(lists);
        LinkedListUtils.print(root);

    }
}
