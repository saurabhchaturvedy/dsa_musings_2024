package Learnings.WM202409.LLD;

public class Entry<K, V> {


    K key;
    V value;

    Entry<K, V> next;


    Entry(K key, V value) {

        this.key = key;
        this.value = value;
        this.next = null;
    }
}
