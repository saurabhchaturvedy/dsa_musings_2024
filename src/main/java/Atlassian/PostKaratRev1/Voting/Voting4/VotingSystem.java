package Atlassian.PostKaratRev1.Voting.Voting4;

import java.util.*;

class VotingSystem {
    public enum TieBreaker {
        MOST_POINTS, FIRST_PLACE_VOTES
    }

    public static List<String> getResults(List<Ballot> ballots, TieBreaker tieBreaker) {
        Map<String, Integer> pointsMap = new HashMap<>();
        Map<String, Integer> firstPlaceVotesMap = new HashMap<>();

        // Process ballots
        for (Ballot ballot : ballots) {
            List<String> rankings = ballot.getRankings();
            if (rankings.size() > 0) {
                String firstPlace = rankings.get(0);
                firstPlaceVotesMap.put(firstPlace, firstPlaceVotesMap.getOrDefault(firstPlace, 0) + 1);
            }
            int rank = 1;
            for (String candidate : rankings) {
                pointsMap.put(candidate, pointsMap.getOrDefault(candidate, 0) + (rank++));
            }
        }

        // Sort candidates based on the total points and tie-breaking strategy
        List<String> candidates = new ArrayList<>(pointsMap.keySet());

        candidates.sort((a, b) -> {
            int pointComparison = pointsMap.get(b).compareTo(pointsMap.get(a));
            if (pointComparison != 0) {
                return pointComparison; // Sort by total points
            }
            if (tieBreaker == TieBreaker.FIRST_PLACE_VOTES) {
                // Tie-break by most first-place votes
                return firstPlaceVotesMap.getOrDefault(b, 0).compareTo(firstPlaceVotesMap.getOrDefault(a, 0));
            }
            return 0; // If tie-breaker is MOST_POINTS, order by points remains
        });

        return candidates;
    }

    public static void main(String[] args) {
        List<Ballot> ballots = Arrays.asList(
            new Ballot(Arrays.asList("Alice", "Bob", "Charlie")),
            new Ballot(Arrays.asList("Bob", "Alice", "Charlie")),
            new Ballot(Arrays.asList("Alice", "Charlie")),
            new Ballot(Arrays.asList("Bob", "Charlie", "Alice"))
        );

        System.out.println("Results (Most Points Wins): " + getResults(ballots, TieBreaker.MOST_POINTS));
        System.out.println("Results (First Place Votes Wins): " + getResults(ballots, TieBreaker.FIRST_PLACE_VOTES));
    }
}