package Learnings.WM202409.Design;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {


    Queue<T> queue;
    int capacity;


    BlockingQueue(int capacity) {

        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }


    public synchronized void enqueue(T item) throws InterruptedException {

        if (queue.size() == capacity) {
            wait();
        }

        queue.add(item);
        notifyAll();
    }


    public synchronized T dequeue() throws InterruptedException {

        if (queue.isEmpty()) {

            wait();
        }

        T item = queue.poll();
        notifyAll();
        return item;
    }


    public synchronized int getSize() {

        return queue.size();
    }

}
