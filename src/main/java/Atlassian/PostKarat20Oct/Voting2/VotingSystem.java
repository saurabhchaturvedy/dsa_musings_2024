package Atlassian.PostKarat20Oct.Voting2;

import java.util.*;

public class VotingSystem {

    static class Candidate {
        String name;
        int points;
        int firstPlaceVotes;
        int firstReachedPointsOrder;

        Candidate(String name) {
            this.name = name;
            this.points = 0;
            this.firstPlaceVotes = 0;
            this.firstReachedPointsOrder = Integer.MAX_VALUE;
        }
    }

    // Function to get sorted candidates with a tie-breaking strategy
    public static List<String> getSortedCandidates(List<List<String>> ballots, String tieBreakingStrategy) {
        Map<String, Candidate> candidateMap = new HashMap<>();
        int ballotOrder = 0;

        // Process each ballot
        for (List<String> ballot : ballots) {
            int[] points = {3, 2, 1}; // Points for 1st, 2nd, and 3rd place
            for (int i = 0; i < ballot.size() && i < 3; i++) {
                String candidateName = ballot.get(i);
                candidateMap.putIfAbsent(candidateName, new Candidate(candidateName));
                Candidate candidate = candidateMap.get(candidateName);
                
                // Add points
                candidate.points += points[i];
                
                // Track first place votes
                if (i == 0) candidate.firstPlaceVotes++;
                
                // Record when the candidate first reached their current points
                if (candidate.firstReachedPointsOrder == Integer.MAX_VALUE || candidate.points - points[i] == 0) {
                    candidate.firstReachedPointsOrder = ballotOrder;
                }
            }
            ballotOrder++;
        }

        // Sort candidates with tie-breaking strategies
        List<Candidate> candidates = new ArrayList<>(candidateMap.values());
        
        candidates.sort((a, b) -> {
            if (b.points != a.points) return Integer.compare(b.points, a.points);
            
            // If points are equal, apply the selected tie-breaking strategy
            switch (tieBreakingStrategy) {
                case "firstToPoints":
                    // Strategy 1: First candidate to reach the largest number of points wins
                    return Integer.compare(a.firstReachedPointsOrder, b.firstReachedPointsOrder);
                    
                case "mostFirstPlaceVotes":
                    // Strategy 2: First candidate with the most 1st place votes wins
                    return Integer.compare(b.firstPlaceVotes, a.firstPlaceVotes);
                    
                default:
                    throw new IllegalArgumentException("Unknown tie-breaking strategy: " + tieBreakingStrategy);
            }
        });

        // Extract sorted names
        List<String> sortedNames = new ArrayList<>();
        for (Candidate candidate : candidates) {
            sortedNames.add(candidate.name);
        }

        return sortedNames;
    }

    public static void main(String[] args) {
        List<List<String>> ballots = new ArrayList<>();
        ballots.add(Arrays.asList("Alice", "Bob", "Charlie"));
        ballots.add(Arrays.asList("Bob", "Charlie", "Alice"));
        ballots.add(Arrays.asList("Bob", "Alice", "Bob"));
        ballots.add(Arrays.asList("Charlie", "Alice", "Bob"));

        // Test strategy 1: First to reach the largest number of points wins
        List<String> result1 = getSortedCandidates(ballots, "firstToPoints");
        System.out.println("Tie-breaking strategy 1 (First to points): " + result1);
        
        // Test strategy 2: First candidate with the most 1st place votes wins
        List<String> result2 = getSortedCandidates(ballots, "mostFirstPlaceVotes");
        System.out.println("Tie-breaking strategy 2 (Most 1st place votes): " + result2);
    }
}
