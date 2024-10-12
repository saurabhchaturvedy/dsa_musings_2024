package Atlassian.PostKarat;

import java.util.List;

public class Ballot {
    private List<String> candidates;

    public Ballot(List<String> candidates) {

        this.candidates = candidates;


    }

    public List<String> getCandidates() {

        return candidates;
    }
}