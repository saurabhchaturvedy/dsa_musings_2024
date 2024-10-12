package Atlassian.PostKarat;

import java.util.*;

public class RankTeamsByVote2 {


    public static String rankTeams(String[] votes) {

        Map<Character, int[]> map = new HashMap<>();

        for (String vote : votes) {

            for (int i = 0; i < vote.length(); i++) {

                char c = vote.charAt(i);
                map.putIfAbsent(c, new int[vote.length()]);
                map.get(c)[i]++;

            }
        }

        List<Character> teams = new ArrayList<>(map.keySet());

        Collections.sort(teams, (a, b) -> {

            int[] voteA = map.get(a);
            int[] voteB = map.get(b);

            for (int i = 0; i < voteA.length; i++) {

                if (voteA[i] != voteB[i]) {
                    return voteB[i] - voteA[i];
                }
            }

            return Character.compare(a, b);

        });

        StringBuilder sb = new StringBuilder();

        for (char team : teams) {

            sb.append(team);
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};

        System.out.println(rankTeams(votes));
    }
}
