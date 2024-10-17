package Atlassian.PostKaratRev1.Voting;

import java.util.*;

public class Voting {


    public List<String> getResults(List<Ballot> ballotList) {

        Map<String, Integer> votingStats = new HashMap<>();

        for (Ballot ballot : ballotList) {
            Map<String, Integer> votes = ballot.getVotes();

            for (Map.Entry<String, Integer> entry : votes.entrySet()) {

                votingStats.put(entry.getKey(), votingStats.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        List<String> sortedCands = new ArrayList<>(votingStats.keySet());

        sortedCands.sort((a, b) -> votingStats.get(b).compareTo(votingStats.get(a)));


        return sortedCands;
    }


    public static void main(String[] args) {


        Map<String, Integer> ballot1 = new HashMap<>();
        ballot1.put("Alice", 5);
        ballot1.put("Bob", 3);
        ballot1.put("Charlie", 2);

        Map<String, Integer> ballot2 = new HashMap<>();
        ballot2.put("Alice", 4);
        ballot2.put("Bob", 2);
        ballot2.put("Charlie", 5);

        Map<String, Integer> ballot3 = new HashMap<>();
        ballot3.put("Alice", 1);
        ballot3.put("Bob", 5);
        ballot3.put("Charlie", 3);

        List<Ballot> ballots = Arrays.asList(new Ballot(ballot1), new Ballot(ballot2), new Ballot(ballot3));

        Voting voting = new Voting();
        List<String> results = voting.getResults(ballots);

        // Output: [Alice, Charlie, Bob] or another order depending on total points
        System.out.println(results);
    }
}
