package Atlassian.PostKaratRev1.Voting;

import java.util.*;

public class Voting2 {


    public List<String> getCandidates(List<Ballot> ballotList, TieStrategy tieStrategy) {

        Map<String, Integer> candidatePoints = new HashMap<>();
        Map<String, Integer> firstPlaceCandidates = new HashMap<>();


        for (Ballot ballot : ballotList) {

            Map<String, Integer> votes = ballot.getVotes();

            String firstFirstCandidate = null;
            int maxVotes = -1;
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {

                String candidate = entry.getKey();
                int points = entry.getValue();

                candidatePoints.put(candidate, candidatePoints.getOrDefault(candidate, 0) + points);


                if (points > maxVotes) {

                    maxVotes = points;
                    firstFirstCandidate = candidate;
                }

            }


            if (firstFirstCandidate != null) {

                firstPlaceCandidates.put(firstFirstCandidate, firstPlaceCandidates.getOrDefault(firstFirstCandidate, 0) + 1);
            }

        }

        List<String> sortedCandidates = new ArrayList<>(candidatePoints.keySet());


        sortedCandidates.sort((a, b) -> {


            int pointCompare = candidatePoints.get(b).compareTo(candidatePoints.get(a));

            if (pointCompare == 0) {
                switch (tieStrategy) {

                    case FIRST_TO_MAX_POINTS -> {
                        return 0;
                    }

                    case FIRST_PLACE_CANDIDATES -> {

                        return firstPlaceCandidates.get(b).compareTo(firstPlaceCandidates.get(a));
                    }
                }
            }

            return pointCompare;
        });

        return sortedCandidates;
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

        Voting2 voting = new Voting2();

        // Test with FIRST_TO_MAX_POINTS strategy
        List<String> resultsFirstToMax = voting.getCandidates(ballots, TieStrategy.FIRST_TO_MAX_POINTS);
        System.out.println("Results with FIRST_TO_MAX_POINTS: " + resultsFirstToMax);

        // Test with MOST_FIRST_PLACE_VOTES strategy
        List<String> resultsFirstPlaceVotes = voting.getCandidates(ballots, TieStrategy.FIRST_PLACE_CANDIDATES);
        System.out.println("Results with MOST_FIRST_PLACE_VOTES: " + resultsFirstPlaceVotes);
    }
}
