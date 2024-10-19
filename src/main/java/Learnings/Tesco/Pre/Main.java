package Learnings.Tesco.Pre;

import Learnings.Tesco.RealStuff19Oct.Cart;

import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        Product rin_soap = new Product(1, "rin_soap", 10);
        Product shampoo = new Product(2, "sunsilk_shampoo", 7);
        Product hajmola = new Product(3, "hajmola", 25);
        Product colgate = new Product(1, "colgate", 5);
        Product paracetamol = new Product(1, "paracetamol", 13);

        List<Product> products = Arrays.asList(rin_soap, shampoo, hajmola, colgate, paracetamol);
        Cart cart = new Cart(products);

        Rule rule1 = new BulkBuyLimitRule(65);
        Rule rule2 = new BulkBuyCategoryQuantityLimitRule(4, "colgate");

        List<Rule> rules = Arrays.asList(rule1, rule2);

        RuleEngine ruleEngine = new RuleEngine(rules);

        System.out.println(ruleEngine.applyRules(cart));
    }
}
