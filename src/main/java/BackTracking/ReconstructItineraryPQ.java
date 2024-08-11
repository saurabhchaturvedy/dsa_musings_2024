package BackTracking;

import java.util.*;

public class ReconstructItineraryPQ {


    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> itinerary = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build the graph with a priority queue for lexical order
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).offer(to);
        }

        // Step 2: Start DFS from "JFK"
        dfs("JFK");

        // Step 3: The itinerary is built in reverse order, so we need to return it as is
        return itinerary;
    }

    private void dfs(String airport) {
        PriorityQueue<String> neighbors = graph.get(airport);
        while (neighbors != null && !neighbors.isEmpty()) {
            dfs(neighbors.poll());
        }
        // Add the airport to the itinerary after visiting all its neighbors
        itinerary.addFirst(airport);
    }


    public static void main(String[] args) {

        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC,LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));

        ReconstructItineraryPQ reconstructItinerary = new ReconstructItineraryPQ();

        List<String> strings = reconstructItinerary.findItinerary(tickets);

        System.out.println(strings);
    }
}
