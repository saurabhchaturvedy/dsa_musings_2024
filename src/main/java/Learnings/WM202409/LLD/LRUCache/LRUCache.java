package Learnings.WM202409.LLD.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {


    ListNode head;
    ListNode tail;

    Map<Integer, ListNode> map;

    int capacity;


    LRUCache(int capacity) {

        this.capacity = capacity;
        this.head = null;
        this.tail = null;
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


    public void attachToHead(ListNode node) {

        if (this.head != null) {

            this.head.prev = node;
        }

        node.next = head;
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
            attachToHead(node);
        } else {


            if (map.size() == capacity) {


                map.remove(this.tail.key);
                remove(tail);
            }


            ListNode node = new ListNode(key, value);

            map.put(key, node);
            attachToHead(node);
            capacity++;
        }
    }


    public int get(int key) {


        if (map.containsKey(key)) {

            ListNode node = map.get(key);

            return node.value;
        }

        return -1;
    }


    public void printLRUCache() {


        for (Map.Entry<Integer, ListNode> entry : map.entrySet()) {


            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    public static void main(String[] args) {


        LRUCache lruCache = new LRUCache(3);


        lruCache.put(1, 10);
        lruCache.put(2, 10);
        lruCache.put(3, 10);
        lruCache.put(4, 10);


        System.out.println(lruCache.get(3));



        lruCache.printLRUCache();


    }
}
