package Atlassian.PostKaratRev1.Voting.Voting5;

import java.util.*;

public class VotingSystem {

    public enum TieBreakingStrategy {
        LARGEST_POINTS_FIRST,
        MOST_FIRST_PLACE_VOTES
    }

    public static List<String> getResults(List<Ballot> ballots, TieBreakingStrategy strategy) {
        // Map to store total points per candidate
        Map<String, Integer> pointsMap = new HashMap<>();
        // Map to store the number of first-place votes per candidate
        Map<String, Integer> firstPlaceVotesMap = new HashMap<>();

        // Assign points for each ballot
        for (Ballot ballot : ballots) {
            List<String> candidates = ballot.getRankedCandidates();
            for (int i = 0; i < candidates.size(); i++) {
                String candidate = candidates.get(i);
                int points = candidates.size() - i; // Higher rank gets more points

                pointsMap.put(candidate, pointsMap.getOrDefault(candidate, 0) + points);

                // Track first-place votes
                if (i == 0) {
                    firstPlaceVotesMap.put(candidate, firstPlaceVotesMap.getOrDefault(candidate, 0) + 1);
                }
            }
        }

        // Create a list of candidates to sort
        List<String> candidates = new ArrayList<>(pointsMap.keySet());

        // Sort candidates based on the selected strategy
        candidates.sort((c1, c2) -> {
            int points1 = pointsMap.get(c1);
            int points2 = pointsMap.get(c2);

            if (points1 != points2) {
                return points2 - points1; // Sort by points descending
            }

            // If tie on points, apply the chosen tie-breaking strategy
            if (strategy == TieBreakingStrategy.MOST_FIRST_PLACE_VOTES) {
                int firstPlaceVotes1 = firstPlaceVotesMap.getOrDefault(c1, 0);
                int firstPlaceVotes2 = firstPlaceVotesMap.getOrDefault(c2, 0);
                return firstPlaceVotes2 - firstPlaceVotes1; // Sort by first-place votes descending
            }

            // Default strategy: first to reach the largest number of points wins
            return 1; // Keep the original order if the points are the same
        });

        return candidates;
    }

    public static void main(String[] args) {
        // Example ballots
        List<Ballot> ballots = Arrays.asList(
                new Ballot(Arrays.asList("Alice", "Bob", "Charlie")),   // Alice: 3 points, Bob: 2 points, Charlie: 1 point
                new Ballot(Arrays.asList("Bob", "Alice", "Charlie")),   // Bob: 3 points, Alice: 2 points, Charlie: 1 point
                new Ballot(Arrays.asList("Charlie", "Alice", "Bob")),   // Charlie: 3 points, Alice: 2 points, Bob: 1 point
                new Ballot(Arrays.asList("Charlie", "Alice", "Bob"))    // Charlie: 3 points, Bob: 2 points, Alice: 1 point
        );

        // Get results with LARGEST_POINTS_FIRST strategy
        List<String> results = getResults(ballots, TieBreakingStrategy.LARGEST_POINTS_FIRST);
        System.out.println("Results (Largest Points First): " + results);

        // Get results with MOST_FIRST_PLACE_VOTES strategy
        results = getResults(ballots, TieBreakingStrategy.MOST_FIRST_PLACE_VOTES);
        System.out.println("Results (Most First-Place Votes): " + results);
    }
}