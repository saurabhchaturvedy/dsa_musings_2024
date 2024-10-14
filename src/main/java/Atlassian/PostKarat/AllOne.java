package Atlassian.PostKarat;

import java.util.*;

class AllOne {

    class ListNode {

        int count;
        Set<String> keys;

        ListNode next;
        ListNode prev;

        ListNode(int count) {

            this.count = count;
            this.keys = new HashSet<>();

        }
    }

    Map<String, ListNode> keyCountMap;

    ListNode head;
    ListNode tail;

    public AllOne() {

        this.keyCountMap = new HashMap<>();

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

        if (!keyCountMap.containsKey(key)) {

            if (this.head.next == tail || this.head.next.count != 1) {

                addNodeAfter(head, 1);
            }

            this.head.next.keys.add(key);
            keyCountMap.put(key, head.next);
        } else {

            ListNode currNode = keyCountMap.get(key);

            int count = currNode.count;

            if (currNode.next == tail || currNode.next.count != count + 1) {

                addNodeAfter(currNode, count + 1);
            }

            currNode.keys.remove(key);
            currNode.next.keys.add(key);

            keyCountMap.put(key, currNode.next);

            if (currNode.keys.isEmpty()) {

                remove(currNode);
            }
        }

    }

    public void dec(String key) {

        if (!keyCountMap.containsKey(key)) {

            return;
        }

        ListNode currNode = keyCountMap.get(key);

        int count = currNode.count;

        currNode.keys.remove(key);

        if (count == 1) {

            keyCountMap.remove(key);
        } else {

            if (currNode.prev == head || currNode.prev.count != count - 1) {

                addNodeAfter(currNode.prev, count - 1);
            }

            currNode.prev.keys.add(key);
            keyCountMap.put(key, currNode.prev);
        }

        if (currNode.keys.isEmpty()) {

            remove(currNode);
        }
    }

    public String getMaxKey() {

        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {

        return head.next == tail ? "" : head.next.keys.iterator().next();
    }

    public static void main(String[] args) {


        AllOne allOne = new AllOne();

        allOne.inc("7");
        allOne.inc("8");

        System.out.println(allOne.getMaxKey());
    }
}