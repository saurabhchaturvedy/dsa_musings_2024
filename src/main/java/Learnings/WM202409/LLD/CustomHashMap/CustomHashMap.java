package Learnings.WM202409.LLD.CustomHashMap;

public class CustomHashMap<K, V> {


    Entry<K, V>[] buckets;
    int size = 0;
    int capacity = 0;


    private static final float LOAD_FACTOR = 0.75f;


    CustomHashMap() {

        this.capacity = 16;
        this.buckets = new Entry[capacity];
    }


    CustomHashMap(int capacity) {

        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }


    public int hashCode(K key) {

        return Math.abs(key.hashCode()) % capacity;
    }


    public void put(K key, V value) {


        if ((float) size / capacity >= LOAD_FACTOR) {

            resize();
        }

        int hashCode = hashCode(key);


        Entry<K, V> entry = new Entry<>(key, value);

        if (buckets[hashCode] == null) {

            buckets[hashCode] = entry;
        } else {


            Entry<K, V> temp = buckets[hashCode];


            while (temp != null) {

                if (temp.key.equals(key)) {

                    temp.value = value;
                }


                if (temp.next != null) {
                    temp = temp.next;
                } else {

                    temp.next = entry;
                }

            }


        }

        size++;

    }


    public V get(K key) {

        int hashCode = hashCode(key);

        Entry<K, V> temp = buckets[hashCode];


        while (temp != null) {

            if (temp.key.equals(key)) {

                return temp.value;
            }

            temp = temp.next;
        }

        return null;

    }


    public void remove(K key) {


        int hashCode = hashCode(key);

        Entry<K, V> temp = buckets[hashCode];

        Entry<K, V> prev = null;


        while (temp != null) {


            if (temp.key.equals(key)) {

                if (prev != null) {

                    prev.next = temp.next;
                } else {


                    prev = temp.next;
                    buckets[hashCode] = prev;
                }

                return;
            }

            prev = temp;
            temp = temp.next;
        }

        size--;
    }


    public void printMap() {


        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        for (int i = 0; i < capacity; i++) {

            Entry<K, V> temp = buckets[i];


            while (temp != null) {

                System.out.print(" key = " + temp.key + " value = " + temp.value);
                temp = temp.next;
            }
            System.out.println();
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }


    public void resize() {


        int newCapacity = 2 * capacity;

        Entry[] newBuckets = new Entry[newCapacity];


        for (int i = 0; i < capacity; i++) {

            Entry<K, V> entry = buckets[i];


            while (entry != null) {

                Entry<K, V> next = entry.next;

                int newHashCode = Math.abs(entry.key.hashCode()) % newCapacity;

                entry.next = newBuckets[newHashCode];
                newBuckets[newHashCode] = entry;
                entry = next;
            }


            capacity = newCapacity;
            buckets = newBuckets;
        }
    }


    public static void main(String[] args) {


        CustomHashMap customHashMap = new CustomHashMap(2);

        customHashMap.put(1, 10);
        customHashMap.put(2, 20);
      //  customHashMap.put(3, 25);


        customHashMap.printMap();

        System.out.println(" map size = " + customHashMap.size);
        customHashMap.remove(10);

        customHashMap.printMap();

        System.out.println(" map capacity = " + customHashMap.capacity);
        System.out.println(" map size = " + customHashMap.size);
    }
}
