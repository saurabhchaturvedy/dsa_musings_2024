package Atlassian.PostKarat20Oct.Voting;

import java.util.*;

public class RankingTie {


    static class Candidate {

        String name;

        int points;

        int arrivalOrder;
        int firstPlaceVotes;


        Candidate(String name, int arrivalOrder) {

            this.name = name;
            this.arrivalOrder = arrivalOrder;
            this.points = 0;
            this.firstPlaceVotes = 0;
        }


        public void addPoints(int points, boolean isFirstPlace) {

            this.points += points;

            if (isFirstPlace) {
                firstPlaceVotes++;
            }
        }
    }


    public enum TieBreakingStrategy {

        FIRST_ARRIVAL,
        MOST_FIRST_PLACE_VOTES
    }


    public static List<String> getTiedRankings(List<Ballot> ballots, TieBreakingStrategy tieBreakingStrategy) {


        Map<String, Candidate> candidateMap = new HashMap<>();

        int arrivalOrder = 0;

        int[] points = {3, 2, 1};

        for (Ballot ballot : ballots) {

            List<String> listOfCandidates = ballot.getRankedCandidates();

            for (int i = 0; i < listOfCandidates.size(); i++) {
                String candidateName = listOfCandidates.get(i);
                int point = points[i];

//                if (!candidateMap.containsKey(listOfCandidates.get(i))) {
//
//                    candidateMap.put(listOfCandidates.get(i), new Candidate(listOfCandidates.get(i), arrivalOrder));
//                }
                candidateMap.putIfAbsent(candidateName, new Candidate(candidateName, arrivalOrder));

                boolean isFirstPlace = (i == 0);
                candidateMap.get(candidateName).addPoints(point, isFirstPlace);
                //  candidateMap.get(listOfCandidates.get(i)).addPoints(points[i], isFirstPlace);

                if (i == 0) {
                    arrivalOrder++;
                }
            }

        }


        List<Candidate> candidateList = new ArrayList<>(candidateMap.values());


        candidateList.sort((a, b) -> {


            if (a.points != b.points) {

                return Integer.compare(b.points, a.points);
            }


            if (tieBreakingStrategy == TieBreakingStrategy.FIRST_ARRIVAL) {

                return Integer.compare(a.arrivalOrder, b.arrivalOrder);
            } else if (tieBreakingStrategy == TieBreakingStrategy.MOST_FIRST_PLACE_VOTES) {

                return Integer.compare(b.firstPlaceVotes, a.firstPlaceVotes);
            }

            return 0;
        });


        List<String> result = new ArrayList<>();

        for (Candidate candidate : candidateList) {

            result.add(candidate.name);
        }

        return result;
    }


    public static void main(String[] args) {


        List<Ballot> ballots = new ArrayList<>();

        ballots.add(new Ballot(Arrays.asList("Alice", "Bob", "Charlie")));
        ballots.add(new Ballot(Arrays.asList("Bob", "Alice")));
        ballots.add(new Ballot(Arrays.asList("Charlie", "Alice")));

        List<String> firstArrivalCandidates = getTiedRankings(ballots, TieBreakingStrategy.FIRST_ARRIVAL);

        System.out.println(firstArrivalCandidates);

        List<String> firstPlaceVotesCandidates = getTiedRankings(ballots, TieBreakingStrategy.MOST_FIRST_PLACE_VOTES);

        System.out.println(firstPlaceVotesCandidates);

    }

}
