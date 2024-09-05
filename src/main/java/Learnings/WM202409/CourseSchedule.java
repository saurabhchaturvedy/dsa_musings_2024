package Learnings.WM202409;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {



    List<Integer>[] adj;
    boolean[] visited;
    boolean[] explored;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        this.adj = new ArrayList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        visited = new boolean[numCourses];
        explored = new boolean[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {

            adj[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        for (int i = 0; i < numCourses; i++) {

            if (!visited[i]) {
                if (isCyclic(i)) {

                    return false;
                }
            }
        }

        return true;
    }

    boolean isCyclic(Integer i) {

        visited[i] = true;

        for (int j : adj[i]) {

            if (!visited[j]) {
                if ((isCyclic(j))) {

                    return true;
                }
            } else if (!explored[j]) {

                return true;

            }
        }

        explored[i] = true;

        return false;
    }


    public static void main(String[] args) {


        CourseSchedule courseSchedule = new CourseSchedule();


        int[][]courses = {{1,0}};
        int[][]courses2 = {{1,0},{0,1}};


        boolean canFinish = courseSchedule.canFinish(2, courses);

        System.out.println(" can finish courses ? "+canFinish);


        boolean canFinish2 = courseSchedule.canFinish(2, courses2);


        System.out.println(" can finish courses ? "+canFinish2);
    }
}
