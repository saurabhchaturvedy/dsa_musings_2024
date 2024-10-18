package Atlassian.PostKaratRev1.Voting.Voting5;

import java.util.List;

class Ballot {
    List<String> rankedCandidates;

    public Ballot(List<String> rankedCandidates) {
        this.rankedCandidates = rankedCandidates;
    }

    public List<String> getRankedCandidates() {
        return rankedCandidates;
    }
}