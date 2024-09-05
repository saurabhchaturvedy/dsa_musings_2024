package Learnings.WM202409;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CourseSchedule2 {


    List<Integer>[] adj;
    boolean[] visited;
    boolean[] explored;
    Stack<Integer> stack;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        adj = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {

            adj[i] = new ArrayList();
        }

        for (int i = 0; i < prerequisites.length; i++) {

            adj[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        visited = new boolean[numCourses];
        explored = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {

            if (!visited[i]) {

                if (isCyclic(i)) {

                    return new int[]{};
                }
            }
        }
        visited = new boolean[numCourses];
        stack = new Stack();
        for (int i = 0; i < numCourses; i++) {

            if (!visited[i]) {

                topologicalSort(i);
            }
        }

        int[] result = new int[stack.size()];

        for (int i = 0; i < result.length; i++) {

            result[i] = stack.pop();
        }

        return result;

    }

    boolean isCyclic(Integer i) {

        visited[i] = true;

        for (Integer j : adj[i]) {

            if (!visited[j]) {

                if (isCyclic(j)) {

                    return true;
                }
            } else if (!explored[j]) {

                return true;
            }

        }

        explored[i] = true;
        return false;
    }

    public void topologicalSort(Integer i) {

        visited[i] = true;

        for (Integer j : adj[i]) {

            if (!visited[j]) {
                topologicalSort(j);
            }
        }

        stack.push(i);
    }


    public static void main(String[] args) {


        int[][] p = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        CourseSchedule2 courseSchedule2 = new CourseSchedule2();


        System.out.println(" Course ordering");

        int[] ordering = courseSchedule2.findOrder(4, p);

        for (int x : ordering) {

            System.out.print(x + " ");
        }
    }
}
