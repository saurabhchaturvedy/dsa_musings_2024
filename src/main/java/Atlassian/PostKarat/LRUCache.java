package Atlassian.PostKarat;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    ListNode head;
    ListNode tail;
    int capacity;
    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {

        this.head = null;
        this.tail = null;
        this.capacity = capacity;
        this.map = new HashMap<>();

    }

    public int get(int key) {

        if (map.containsKey(key)) {

            ListNode node = map.get(key);
            remove(node);
            moveToHead(node);

            return node.value;
        }

        return -1;

    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {

            ListNode node = map.get(key);
            node.value = value;
            remove(node);
            moveToHead(node);
        } else {

            if (map.size() == this.capacity) {

                map.remove(this.tail.key);
                remove(this.tail);
            }

            ListNode node = new ListNode(key, value);
            map.put(key, node);
            moveToHead(node);
        }

    }

    public void remove(ListNode node) {
        // 2-> 4->6->7->8
        if (node.prev != null) {

            node.prev.next = node.next;
        } else {

            this.head = node.next;
        }

        if (node.next != null) {

            node.next.prev = node.prev;
        } else {

            this.tail = node.prev;
        }
    }

    public void moveToHead(ListNode node) {

        if (this.head != null) {

            this.head.prev = node;
        }

        node.next = this.head;
        node.prev = null;
        this.head = node;

        if (tail == null) {

            this.tail = node;
        }

    }

    class ListNode {

        int key;
        int value;
        ListNode prev;
        ListNode next;

        ListNode(int key, int value) {

            this.key = key;
            this.value = value;
        }

        public String toString() {

            return this.key + "-" + this.value;
        }

    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1);
        lruCache.put(2, 2);

        System.out.println(lruCache.get(1));

        System.out.println(lruCache.map);
        lruCache.put(3, 3);
        System.out.println(lruCache.map);
        System.out.println(lruCache.get(2));

        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }


}










