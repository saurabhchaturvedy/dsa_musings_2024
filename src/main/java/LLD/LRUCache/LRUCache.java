package LLD.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {


    ListNode head;
    ListNode tail;

    int capacity;

    Map<Integer, ListNode> map;


    LRUCache(int capacity) {

        this.head = null;
        this.tail = null;
        this.capacity = capacity;
        this.map = new HashMap<>();
    }


    public void remove(ListNode node) {

        if (node.prev != null) {

            node.prev.next = node.next;
        } else {

            this.head = node.next;
        }

        if (node.next != null) {

            node.next.prev = node.prev;
        } else {

            tail = node.prev;
        }
    }


    public void setHead(ListNode node) {

        if (this.head != null) {
            this.head.prev = node;
        }


        node.next = this.head;
        node.prev = null;
        this.head = node;

        if (this.tail == null) {
            this.tail = node;
        }
    }


    public void put(int key, int value) {

        if (map.containsKey(key)) {

            ListNode node = map.get(key);
            node.value = value;
            remove(node);
            setHead(node);
        } else {

            if (map.size() == this.capacity) {
                map.remove(this.tail.key);
                remove(tail);
            }

            ListNode node = new ListNode(key, value);
            map.put(key,node);
            setHead(node);
        }
    }


    public int get(int key) {

        if (map.containsKey(key)) {

            ListNode node = map.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }

        return -1;
    }


    public static void main(String[] args) {


        LRUCache lruCache = new LRUCache(3);


        lruCache.put(1, 10);
        lruCache.put(2, 20);
        lruCache.put(3, 30);


        System.out.println(" get(3) " + lruCache.get(3));

        lruCache.put(4,50);

        System.out.println(" get(3) " + lruCache.get(3));
    }
}
