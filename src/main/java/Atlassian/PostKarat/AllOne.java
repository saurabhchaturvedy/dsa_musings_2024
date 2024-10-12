package Atlassian.PostKarat;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class AllOne {

    private class ListNode {

        int count;
        LinkedHashSet<String> keys;
        ListNode next;
        ListNode prev;

        ListNode(int count) {

            this.count = count;
            this.keys = new LinkedHashSet<>();
        }
    }

    Map<String, ListNode> map;

    ListNode head;
    ListNode tail;

    public AllOne() {

        this.map = new HashMap<>();
        this.head = new ListNode(0);
        this.tail = new ListNode(0);

        this.head.next = tail;
        this.tail.prev = head;
    }

    public ListNode addNodeAfter(ListNode prevNode, int count) {

        ListNode newNode = new ListNode(count);

        newNode.next = prevNode.next;
        newNode.prev = prevNode;

        prevNode.next.prev = newNode;

        prevNode.next = newNode;

        return newNode;
    }

    public void remove(ListNode node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void inc(String key) {

        if (!map.containsKey(key)) {

            if (head.next == tail || head.next.count != 1) {
                addNodeAfter(head, 1);
            }

            head.next.keys.add(key);
            map.put(key, head.next);
        } else {

            ListNode currNode = map.get(key);

            int count = currNode.count;

            if (currNode.next == tail || currNode.next.count != count + 1) {

                addNodeAfter(currNode, count + 1);
            }

            currNode.next.keys.add(key);
            map.put(key, currNode.next);
            currNode.keys.remove(key);

            if (currNode.keys.isEmpty()) {
                remove(currNode);
            }
        }

    }

    public void dec(String key) {

        if (!map.containsKey(key)) {
            return;
        }

        ListNode prevNode = map.get(key);

        int count = prevNode.count;

        prevNode.keys.remove(key);

        if (count == 1) {

            map.remove(key);
        } else {

            if (prevNode.prev == head || prevNode.prev.count != count - 1) {
                addNodeAfter(prevNode.prev, count - 1);

            }

            prevNode.prev.keys.add(key);
            map.put(key, prevNode.prev);
        }

        if (prevNode.keys.isEmpty()) {

            map.remove(prevNode);
        }

    }

    public String getMaxKey() {

        return this.tail.prev == head ? "" : this.tail.prev.keys.iterator().next();

    }

    public String getMinKey() {

        return this.head.next == tail ? "" : this.head.next.keys.iterator().next();
    }


    public static void main(String[] args) {


        AllOne allOne = new AllOne();

        allOne.inc("7");
        allOne.inc("8");

        System.out.println(allOne.getMaxKey());
    }
}