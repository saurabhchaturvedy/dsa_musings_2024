package Atlassian.Karat;

import java.util.LinkedList;

public class HitCounter {


    int lastFiveMinutesHitsCount;
    LinkedList<Integer> hits;


    HitCounter() {

        this.lastFiveMinutesHitsCount = 0;
        this.hits = new LinkedList<>();
    }


    public void hit(int timestamp) {

        this.hits.addFirst(timestamp);
        ++lastFiveMinutesHitsCount;
    }


    public int getHits(int timestamp) {

        if (hits.isEmpty()) {
            return 0;
        }

        pruneOldHits(timestamp);
        return lastFiveMinutesHitsCount;
    }

    private void pruneOldHits(int timestamp) {


        int last = hits.getLast();

        while (last <= timestamp - 300) {

            hits.pollLast();
            --lastFiveMinutesHitsCount;

            if (hits.isEmpty()) {
                return;
            }


            last = hits.getLast();
        }
    }

    public static void main(String[] args) {


        HitCounter counter = new HitCounter();

        // Hit at time 1.
        counter.hit(1);

        // Hit at time 2.
        counter.hit(2);

        // Hit at time 3.
        counter.hit(3);

        // Get hits at time 4, should return 3.
        System.out.println(counter.getHits(4));
    }
}
