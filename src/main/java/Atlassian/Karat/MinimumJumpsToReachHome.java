package Atlassian.Karat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MinimumJumpsToReachHome {


    class Position {

        int val;
        int direction;

        Position(int val, int direction) {

            this.val = val;
            this.direction = direction;
        }

        public String toString() {

            return this.val + " " + this.direction;
        }
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {

        if (x == 0) {
            return 0;
        }

        int steps = 0;
        int furthest = 10000;

        Set<Integer> forbiddenSpots = new HashSet<>();
        Set<String> visited = new HashSet<>();

        for (int f : forbidden) {

            forbiddenSpots.add(f);
        }

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(0, 0));
        visited.add(0 + " " + 0);
        steps++;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                Position currentPosition = queue.poll();

                System.out.println(" Visisted " + currentPosition.val);

                int next_a = currentPosition.val + a;

                if (next_a == x) {

                    return steps;
                }

                if (next_a >= 0 && next_a <= furthest && !forbiddenSpots.contains(next_a)
                        && visited.add(next_a + " " + 1)) {

                    queue.add(new Position(next_a, 1));
                }

                if (currentPosition.direction == 1) {

                    int next_b = currentPosition.val - b;

                    if (next_b == x) {
                        return steps;
                    }

                    if (next_b >= 0 && next_b <= furthest && !forbiddenSpots.contains(next_b)
                            && visited.add(next_b + " " + 0)) {

                        queue.add(new Position(next_b, 0));
                    }
                }
            }

            steps++;
        }

        return -1;
    }


    public static void main(String[] args) {


        int[] forbidden = {14, 4, 18, 1, 15};

        int a = 3;
        int b = 15;

        int x = 9;

        MinimumJumpsToReachHome minimumJumpsToReachHome = new MinimumJumpsToReachHome();

        System.out.println(" minimum jumps -> " + minimumJumpsToReachHome.minimumJumps(forbidden, a, b, x));
    }
}
