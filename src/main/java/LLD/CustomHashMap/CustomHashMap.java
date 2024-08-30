package LLD.CustomHashMap;

public class CustomHashMap<K, V> {


    Entry<K, V>[] buckets;
    int capacity;
    int size = 0;

    public static final float LOAD_FACTOR = 0.75f;


    CustomHashMap() {

        this.capacity = 16;
        this.buckets = new Entry[capacity];
    }


    CustomHashMap(int capacity) {

        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }


    public void put(K key, V value) {

        if ((float) size / capacity >= LOAD_FACTOR) {

            resize();
        }

        int hash = hash(key);

        Entry<K, V> entry = new Entry<>(key, value);
        Entry<K, V> temp = buckets[hash];

        if (buckets[hash] == null) {

            buckets[hash] = entry;
        } else {


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

    private void resize() {


        int newCapacity = 2 * capacity;

        Entry<K, V>[] newBuckets = new Entry[newCapacity];

        for (int i = 0; i < capacity; i++) {

            Entry<K, V> entry = buckets[i];

            while (entry != null) {

                Entry<K, V> next = entry.next;

                int hash = Math.abs(entry.key.hashCode()) % newCapacity;

                entry.next = newBuckets[hash];
                newBuckets[hash] = entry;
                entry = next;
            }
        }

        capacity = newCapacity;
        buckets = newBuckets;
    }


    public V get(K key) {

        int hash = hash(key);

        Entry<K, V> temp = buckets[hash];

        while (temp != null) {

            if (temp.key.equals(key)) {

                return temp.value;
            }
        }

        return null;

    }


    public void remove(K key) {

        int hash = hash(key);

        Entry<K, V> temp = buckets[hash];
        Entry<K, V> prev = null;

        while (temp != null) {

            if (prev != null) {

                prev.next = temp.next;
            } else {

                prev = temp.next;
                buckets[hash] = prev;
            }

            prev = temp;
            temp = temp.next;

        }
    }


    public void printMap() {

        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        for (int i = 0; i < capacity; i++) {

            Entry<K, V> temp = buckets[i];

            while (temp != null) {

                System.out.println(" key : " + temp.key + " value : " + temp.value);
                temp = temp.next;

            }
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }


    public int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }


    public static void main(String[] args) {


        CustomHashMap customHashMap = new CustomHashMap();


        customHashMap.put("A", 100);
        customHashMap.put("B", 200);
        customHashMap.put("C", 300);
        customHashMap.put("D", 400);


        customHashMap.printMap();

        customHashMap.remove("D");

        customHashMap.printMap();

    }
}
