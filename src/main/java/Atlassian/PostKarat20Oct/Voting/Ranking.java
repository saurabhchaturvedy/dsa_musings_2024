package Atlassian.PostKarat20Oct.Voting;

import java.util.*;

public class Ranking {


    public static List<String> getRankings(List<Ballot> ballots) {


        Map<String, Integer> candidateToVotesMap = new HashMap<>();

        int[] points = {3, 2, 1};

        for (Ballot ballot : ballots) {

            List<String> candidatesInBallot = ballot.getRankedCandidates();

            for (int i = 0; i < candidatesInBallot.size(); i++) {

                candidateToVotesMap.put(candidatesInBallot.get(i), candidateToVotesMap.getOrDefault(candidatesInBallot.get(i), 0) + points[i]);
            }
        }


        List<String> listOfCandidates = new ArrayList<>(candidateToVotesMap.keySet());
        listOfCandidates.sort((a, b) -> candidateToVotesMap.get(b).compareTo(candidateToVotesMap.get(a)));

        return listOfCandidates;
    }


    public static void main(String[] args) {


        List<Ballot> ballots = new ArrayList<>();
        ballots.add(new Ballot(Arrays.asList("Alice", "Bob", "Charlie")));
        ballots.add(new Ballot(Arrays.asList("Bob", "Alice", "Charlie")));
        ballots.add(new Ballot(Arrays.asList("Charlie", "Alice", "Bob")));

        List<String> results = getRankings(ballots);
        System.out.println(results);

        // Alice 3 + 2 + 2 = 7
        // Bob 2 + 3 + 1 = 6
        // Charlie 3 + 1 + 1 = 5
    }
}

//overall time complexity of the solution is:
//
//𝑂
//        (
//                𝑏
//×
//        𝑟
//        +
//        𝑚
//        log
//        ⁡
//        𝑚
//        )
//O(b×r+mlogm)
//Where:
//
//𝑏
//b is the number of ballots,
//        𝑟
//r is the maximum number of ranked candidates in a ballot,
//𝑚
//m is the number of unique candidates.