package EricProgramming.BackTracking;

import java.util.*;

public class ReconstructItinerary {


    Map<String, List<String>> graph = new HashMap<>();
    List<String> path = new LinkedList<>();


    public List<String> reconstructItinerary(List<List<String>> itineraries) {


        for (List<String> airport : itineraries) {

            String start = airport.get(0);
            String end = airport.get(1);

            if (!graph.containsKey(start)) {
                graph.put(start, new LinkedList<>());
            }

            if (!graph.containsKey(end)) {
                graph.put(end, new LinkedList<>());
            }

            graph.get(start).add(end);
        }

        for (String start : graph.keySet()) {
            Collections.sort(graph.get(start));
        }


        dfs("JFK", itineraries.size());
        return path;
    }

    private boolean dfs(String start, int edgesCount) {

        List<String> list = graph.get(start);
        path.add(start);

        if (list.isEmpty()) {
            if (edgesCount == 0) return true;
            return false;
        }


        for (int i = 0; i < list.size(); i++) {

            String top = list.remove(i);

            if (dfs(top, edgesCount - 1)) return true;

            list.add(i, top);

            path.remove(path.size() - 1);
        }

        return false;
    }


    public static void main(String[] args) {


        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC,LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));

        ReconstructItinerary reconstructItinerary = new ReconstructItinerary();

        List<String> strings = reconstructItinerary.reconstructItinerary(tickets);

        System.out.println(strings);
    }
}
