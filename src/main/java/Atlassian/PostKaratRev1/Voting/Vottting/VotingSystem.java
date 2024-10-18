package Atlassian.PostKaratRev1.Voting.Vottting;

import java.util.*;

public class VotingSystem {

    public List<String> getResults(List<Ballot> ballots, String tieBreakingStrategy) {
        Map<String, Integer> totalPoints = new HashMap<>();
        Map<String, Integer> firstPlaceVotes = new HashMap<>();
        Map<String, Integer> firstAppearance = new HashMap<>();
        int ballotCounter = 0;

        // Process each ballot
        for (Ballot ballot : ballots) {
            List<String> rankedCandidates = ballot.getRankedCandidates();
            for (int i = 0; i < rankedCandidates.size(); i++) {
                String candidate = rankedCandidates.get(i);
                int points = rankedCandidates.size() - i; // Higher rank gets more points

                totalPoints.put(candidate, totalPoints.getOrDefault(candidate, 0) + points);

                // Count first-place votes
                if (i == 0) {
                    firstPlaceVotes.put(candidate, firstPlaceVotes.getOrDefault(candidate, 0) + 1);
                }

                // Record first appearance for tie-breaking by earliest reach
                if (!firstAppearance.containsKey(candidate)) {
                    firstAppearance.put(candidate, ballotCounter);
                }
            }
            ballotCounter++;
        }

        // Create a list of candidates
        List<String> candidates = new ArrayList<>(totalPoints.keySet());

        // Sort candidates by total points and tie-breaking strategy
        candidates.sort((c1, c2) -> {
            int comparePoints = Integer.compare(totalPoints.get(c2), totalPoints.get(c1));

            if (comparePoints == 0) {
                // Tie-breaking strategies
                if (tieBreakingStrategy.equals("earliest_high_points")) {
                    return Integer.compare(firstAppearance.get(c1), firstAppearance.get(c2));
                } else if (tieBreakingStrategy.equals("most_first_place_votes")) {
                    return Integer.compare(firstPlaceVotes.get(c2), firstPlaceVotes.get(c1));
                }
            }
            return comparePoints;
        });

        return candidates;
    }

    public static void main(String[] args) {


        List<Ballot> ballots = Arrays.asList(
                new Ballot(Arrays.asList("Alice", "David", "Charlie")),
                new Ballot(Arrays.asList("David", "Alice", "Bob")),
                new Ballot(Arrays.asList("Charlie", "David", "Alice")),
                new Ballot(Arrays.asList("Alice", "Bob", "Charlie")),
                new Ballot(Arrays.asList("Charlie", "David", "Alice"))
        );



        VotingSystem vs = new VotingSystem();
        System.out.println(vs.getResults(ballots, "earliest_high_points"));
        System.out.println(vs.getResults(ballots, "most_first_place_votes"));
    }
}