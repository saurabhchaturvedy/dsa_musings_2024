package Atlassian.PostKarat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankTeamsByVote {


    public static String rankTeams(String[] votes) {

        int col = votes[0].length();

        int[][] voteCount = new int[26][col];

        for (String vote : votes) {

            for (int position = 0; position < col; position++) {

                voteCount[vote.charAt(position) - 'A'][position]++;
            }
        }

        List<Character> teams = new ArrayList<>();

        for (String vote : votes) {

            for (char c : vote.toCharArray()) {

                if (!teams.contains(c)) {

                    teams.add(c);
                }

            }
        }

        Collections.sort(teams, (a, b) -> {

            for (int position = 0; position < col; position++) {

                if (voteCount[b - 'A'][position] != voteCount[a - 'A'][position]) {

                    return voteCount[b - 'A'][position] - voteCount[a - 'A'][position];
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


        System.out.println(rankTeams(votes));
    }
}
