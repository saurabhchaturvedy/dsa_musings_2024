package Atlassian.PostKaratRev1.Voting;

import java.util.Map;

public class Ballot {


    Map<String, Integer> votes;


    Ballot(Map<String, Integer> votes) {

        this.votes = votes;
    }


    public Map<String, Integer> getVotes() {

        return votes;
    }
}
