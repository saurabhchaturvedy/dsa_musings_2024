package Atlassian.PostKarat20Oct.Voting;

import java.util.*;

public class VotingSystem {

    // Class to store each candidate with their total points and first-place votes
    static class Candidate {
        String name;
        int points;
        int firstPlaceVotes;
        int arrivalOrder; // To track when the candidate was first seen (for tie-breaking)

        public Candidate(String name, int arrivalOrder) {
            this.name = name;
            this.points = 0;
            this.firstPlaceVotes = 0;
            this.arrivalOrder = arrivalOrder;
        }

        // Add points and record first-place vote
        public void addPoints(int points, boolean isFirstPlace) {
            this.points += points;
            if (isFirstPlace) {
                this.firstPlaceVotes++;
            }
        }
    }

    // Enum to represent different tie-breaking strategies
    public enum TieBreakingStrategy {
        FIRST_ARRIVAL,    // First candidate to the largest number of points wins
        MOST_FIRST_PLACE  // First candidate with the most first-place votes wins
    }

    public static List<String> getResults(List<Ballot> ballots, TieBreakingStrategy strategy) {
        // Map to store candidate names and their candidate objects (points, votes, etc.)
        Map<String, Candidate> candidateMap = new HashMap<>();
        
        // Assign points based on ranking
        int[] points = {3, 2, 1}; // Can be adjusted if needed
        
        // Track the order in which candidates are processed (for the arrival order tie-breaker)
        int arrivalOrder = 0;

        // Process each ballot
        for (Ballot ballot : ballots) {
            List<String> rankedCandidates = ballot.getRankedCandidates();
            for (int i = 0; i < rankedCandidates.size(); i++) {
                String candidateName = rankedCandidates.get(i);
                int point = points[i];
                
                // If the candidate is seen for the first time, initialize their record
                candidateMap.putIfAbsent(candidateName, new Candidate(candidateName, arrivalOrder));
                
                // Update the candidate's points and record first-place votes
                boolean isFirstPlace = (i == 0); // First place is index 0
                candidateMap.get(candidateName).addPoints(point, isFirstPlace);
                
                if (i == 0) {
                    arrivalOrder++; // Increment order for the first time candidate is encountered
                }
            }
        }
        
        // Create a list from the candidate map
        List<Candidate> candidates = new ArrayList<>(candidateMap.values());
        
        // Sort candidates based on total points and tie-breaking strategy
        candidates.sort((c1, c2) -> {
            // First compare by points
            if (c1.points != c2.points) {
                return Integer.compare(c2.points, c1.points); // Descending order of points
            }
            // If points are the same, apply the selected tie-breaking strategy
            if (strategy == TieBreakingStrategy.FIRST_ARRIVAL) {
                return Integer.compare(c1.arrivalOrder, c2.arrivalOrder); // First candidate seen wins
            } else if (strategy == TieBreakingStrategy.MOST_FIRST_PLACE) {
                return Integer.compare(c2.firstPlaceVotes, c1.firstPlaceVotes); // Most first-place votes wins
            }
            return 0; // If both points and tie-breaker are the same
        });
        
        // Extract candidate names in sorted order
        List<String> result = new ArrayList<>();
        for (Candidate candidate : candidates) {
            result.add(candidate.name);
        }
        
        return result;
    }

    public static void main(String[] args) {
        List<Ballot> ballots = new ArrayList<>();

        // Ballot #1: Alice, Bob, Charlie
        ballots.add(new Ballot(Arrays.asList("Alice", "Bob", "Charlie")));

        // Ballot #2: Bob, Alice, Charlie
        ballots.add(new Ballot(Arrays.asList("Bob", "Alice", "Charlie")));

        // Ballot #3: Charlie, Bob, Alice
        ballots.add(new Ballot(Arrays.asList("Charlie", "Bob", "Alice")));

        // Ballot #4: Bob, Alice, Charlie
        ballots.add(new Ballot(Arrays.asList("Bob", "Alice", "Charlie")));

        // Ballot #5: Charlie, Alice, Bob
        ballots.add(new Ballot(Arrays.asList("Charlie", "Alice", "Bob")));

        // Get and print the results with the "First Arrival" tie-breaking strategy
        List<String> results = VotingSystem.getResults(ballots, VotingSystem.TieBreakingStrategy.FIRST_ARRIVAL);
        System.out.println("First Arrival Tie-Breaking: " + results);

        // Get and print the results with the "Most First-Place Votes" tie-breaking strategy
        results = VotingSystem.getResults(ballots, VotingSystem.TieBreakingStrategy.MOST_FIRST_PLACE);
        System.out.println("Most First-Place Votes Tie-Breaking: " + results);
    }
}