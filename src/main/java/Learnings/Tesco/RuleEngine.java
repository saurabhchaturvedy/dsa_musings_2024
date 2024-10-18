package Learnings.Tesco;

import java.util.List;

public class RuleEngine {


    List<Rule> rules;

    public RuleEngine(List<Rule> rules) {
        this.rules = rules;
    }

    public String applyRules(Cart cart) {
        for (Rule rule : rules) {

            if (!rule.applyRule(cart)) {
                return "BREACHED : " + rule.getName();
            }
        }

        return "MET";
    }
}
