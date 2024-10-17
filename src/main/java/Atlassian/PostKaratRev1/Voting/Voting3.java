package Atlassian.PostKaratRev1.Voting;

import java.util.*;

public class Voting3 {


    public List<String> getResults(List<Ballot> ballots, TieStrategy strategy) {
        Map<String, Integer> candidatePoints = new HashMap<>();
        Map<String, Integer> firstPlaceVotes = new HashMap<>(); // Tracks 1st place votes
        Map<String, Integer> candidateFirstMax = new HashMap<>(); // Tracks the first appearance of max points

        int appearanceOrder = 0;

        // Process each ballot
        for (Ballot ballot : ballots) {
            Map<String, Integer> votes = ballot.getVotes();
            String firstPlaceCandidate = null;
            int maxVote = -1;

            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                String candidate = entry.getKey();
                int points = entry.getValue();

                candidatePoints.put(candidate, candidatePoints.getOrDefault(candidate, 0) + points);

                // Track 1st place votes
                if (points > maxVote) {
                    maxVote = points;
                    firstPlaceCandidate = candidate;
                }

                // Track when the candidate first reaches their current max score
                if (!candidateFirstMax.containsKey(candidate)) {
                    candidateFirstMax.put(candidate, appearanceOrder++);
                }
            }

            // Increment first place votes for the top candidate in this ballot
            if (firstPlaceCandidate != null) {
                firstPlaceVotes.put(firstPlaceCandidate, firstPlaceVotes.getOrDefault(firstPlaceCandidate, 0) + 1);
            }
        }

        // Sort candidates based on total points
        List<String> sortedCandidates = new ArrayList<>(candidatePoints.keySet());
        sortedCandidates.sort((a, b) -> {
            int pointCompare = candidatePoints.get(b).compareTo(candidatePoints.get(a));

            // If points are tied, use the tie-breaking strategy
            if (pointCompare == 0) {
                switch (strategy) {
                    case FIRST_TO_MAX_POINTS:
                        // Break ties based on the appearance order
                        return candidateFirstMax.get(a).compareTo(candidateFirstMax.get(b));
                    case FIRST_PLACE_CANDIDATES:
                        // Break ties based on the number of first-place votes
                        return firstPlaceVotes.getOrDefault(b, 0).compareTo(firstPlaceVotes.getOrDefault(a, 0));
                }
            }
            return pointCompare;
        });

        return sortedCandidates;
    }

    public static void main(String[] args) {
        // Create ballots where each ballot has three candidates
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

        Voting3 voting = new Voting3();

        // Test with FIRST_TO_MAX_POINTS strategy
        List<String> resultsFirstToMax = voting.getResults(ballots,TieStrategy.FIRST_TO_MAX_POINTS);
        System.out.println("Results with FIRST_TO_MAX_POINTS: " + resultsFirstToMax);

        // Test with MOST_FIRST_PLACE_VOTES strategy
        List<String> resultsFirstPlaceVotes = voting.getResults(ballots, TieStrategy.FIRST_PLACE_CANDIDATES);
        System.out.println("Results with MOST_FIRST_PLACE_VOTES: " + resultsFirstPlaceVotes);
    }
}
