package Atlassian.PostKaratRev1.Voting.Voting4;

import java.util.List;

class Ballot {
    List<String> rankings; // Ranked candidates in order

    public Ballot(List<String> rankings) {
        this.rankings = rankings;
    }

    public List<String> getRankings() {
        return rankings;
    }
}