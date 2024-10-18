package Atlassian.PostKaratRev1.Voting.Voting6;

import java.util.*;

public class VotingSystem {

    public List<String> getResults(List<Ballot> ballots, TieBreakingStrategy strategy) {
        Map<String, Integer> points = new HashMap<>();
        Map<String, Integer> firstPlaceVotes = new HashMap<>();

        for (Ballot ballot : ballots) {
            List<String> candidates = ballot.getCandidates();
            if (candidates.isEmpty()) continue;

            String topCandidate = candidates.get(0);
            points.put(topCandidate, points.getOrDefault(topCandidate, 0) + 1);
            firstPlaceVotes.put(topCandidate, firstPlaceVotes.getOrDefault(topCandidate, 0) + 1);

            for (int i = 1; i < candidates.size(); i++) {
                points.put(candidates.get(i), points.getOrDefault(candidates.get(i), 0) + 1);
            }
        }
        System.out.println(" points = "+points);
        System.out.println("<<<>>>>");
        System.out.println(firstPlaceVotes);
        List<String> sortedCandidates = new ArrayList<>(points.keySet());
        sortedCandidates.sort((c1, c2) -> {
            int pointCompare = Integer.compare(points.get(c2), points.get(c1));
            if (pointCompare != 0) {
                return pointCompare;
            }
            return strategy == TieBreakingStrategy.FIRST_PLACE_VOTES
                    ? Integer.compare(firstPlaceVotes.getOrDefault(c2, 0), firstPlaceVotes.getOrDefault(c1, 0))
                    : 0; // Default to 0 if the strategy is POINTS_FIRST
        });

        return sortedCandidates;
    }

    public static void main(String[] args) {
        // Sample test case
        List<Ballot> ballots = Arrays.asList(
                new Ballot(Arrays.asList("Alice", "Bob", "Charlie")),
                new Ballot(Arrays.asList("Charlie", "Alice","Bob")),
                new Ballot(Arrays.asList("Charlie", "Bob","Alice"))
        );

        // Instantiate the voting system
        VotingSystem votingSystem = new VotingSystem();

        // Run the test case using both tie-breaking strategies
        List<String> resultsPointsFirst = votingSystem.getResults(ballots, TieBreakingStrategy.POINTS_FIRST);
        List<String> resultsFirstPlaceVotes = votingSystem.getResults(ballots, TieBreakingStrategy.FIRST_PLACE_VOTES);

        // Print results
        System.out.println("Results using Points First Tie-Breaking Strategy:");
        System.out.println(resultsPointsFirst);

        System.out.println("\nResults using First Place Votes Tie-Breaking Strategy:");
        System.out.println(resultsFirstPlaceVotes);
    }
}