package Atlassian.PostKarat18Oct.PostKaratSat19Oct;

import java.util.*;

public class RankTeamsByVote {


    public static String rankTeamsByVote(String[] votes) {


        Map<Character, int[]> map = new HashMap<>();

        int numLength = votes[0].length();

        for (Character c : votes[0].toCharArray()) {

            map.put(c, new int[numLength]);
        }

        for (String vote : votes) {

            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                map.get(c)[i]++;
            }
        }


        List<Character> teams = new ArrayList<>(map.keySet());

        Collections.sort(teams, (a, b) -> {

            int[] voteA = map.get(a);
            int[] voteB = map.get(b);

            for (int i = 0; i < numLength; i++) {

                if (voteA[i] != voteB[i]) {
                    return voteB[i] - voteA[i];
                }
            }

            return a - b;
        });


        StringBuilder sb = new StringBuilder();

        for (char team : teams) {
            sb.append(team);
        }

        return sb.toString();
    }


    public static void main(String[] args) {


        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};

        System.out.println(rankTeamsByVote(votes));
    }
}
