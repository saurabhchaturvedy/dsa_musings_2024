package Atlassian.PostKaratRev1.Voting.Votting;

import java.util.Map;

class Ballot {
    private Map<String, Integer> votes; // Candidate name and points

    public Ballot(Map<String, Integer> votes) {
        this.votes = votes;
    }

    public Map<String, Integer> getVotes() {
        return votes;
    }
}