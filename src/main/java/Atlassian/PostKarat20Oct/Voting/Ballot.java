package Atlassian.PostKarat20Oct.Voting;

import java.util.List;

public class Ballot {


    List<String> rankedCandidates;


    public Ballot(List<String> rankedCandidates) {
        this.rankedCandidates = rankedCandidates;
    }


    public List<String> getRankedCandidates() {
        return rankedCandidates;
    }
}