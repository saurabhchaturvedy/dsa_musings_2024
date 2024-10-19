package Learnings.Tesco.RealStuff19Oct;

import java.util.List;

public class TescoRuleEngine implements RuleEngine {


    List<Rule> rules;


    public TescoRuleEngine(List<Rule> rules) {
        this.rules = rules;
    }


    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public void deleteRule(Rule rule) {
        this.rules.remove(rule);
    }


    @Override
    public RuleStatus fireRules(Cart cart) {

        for (Rule rule : rules) {
            if (!rule.applyRule(cart)) {

                return RuleStatus.BREACHED;

            }
        }

        return RuleStatus.MET;
    }
}
