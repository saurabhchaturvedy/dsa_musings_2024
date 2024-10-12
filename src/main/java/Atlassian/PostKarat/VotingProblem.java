package Atlassian.PostKarat;

import java.util.*;

public class VotingProblem {


    public static List<String> getRankedVoting(List<Ballot> ballots) {


        Map<String, List<Integer>> map = new HashMap<>();

        for (Ballot ballot : ballots) {

            List<String> candidates = ballot.getCandidates();

            for (int i = 0; i < candidates.size(); i++) {

                String candidate = candidates.get(i);
                map.putIfAbsent(candidate, new ArrayList<>(Collections.nCopies(candidates.size(), 0)));
                map.get(candidate).set(i, map.get(candidate).get(i) + 1);
            }
        }

        List<String> finalCandidates = new ArrayList<>(map.keySet());


        Collections.sort(finalCandidates, (a, b) -> {

            List<Integer> votesA = map.get(a);
            List<Integer> votesB = map.get(b);

            for (int i = 0; i < votesA.size(); i++) {

                if (!votesA.get(i).equals(votesB.get(i))) {

                    return votesB.get(i) - votesA.get(i);
                }
            }

            return a.compareTo(b);
        });

        return finalCandidates;


    }


    public static void main(String[] args) {

        List<Ballot> ballots = List.of(
                new Ballot(List.of("Alice", "Bob", "Charlie")),
                new Ballot(List.of("Alice", "Charlie", "Bob")),
                new Ballot(List.of("Alice", "Bob", "Charlie")),
                new Ballot(List.of("Alice", "Charlie", "Bob")),
                new Ballot(List.of("Alice", "Charlie", "Bob"))
        );

        System.out.println(getRankedVoting(ballots));
    }

}

