package Learnings.Tesco.RealStuff19Oct;

import java.util.Arrays;
import java.util.List;

public class RuleExecutor {


    public static void main(String[] args) {


//        Item-1 -> productid=1, category=Paracetamol, quantity=3
//        Item-2 -> productid=2, category=analgesic, quantity=3
//        Item-3 -> productid=3, category=chocolate, quantity=8
//        Item-4 -> productid=4, category= Paracetamol, quantity=2


        Product paracetamol = new Product(1, "Paracetamol", 3);
        Product analgesic = new Product(2, "analgesic", 3);
        Product chocolate = new Product(3, "chocolate", 8);
        Product paracetamol2 = new Product(4, "Paracetamol", 4);


        List<Product> products = Arrays.asList(paracetamol, analgesic, chocolate, paracetamol2);
        Cart cart = new Cart(products);


        List<Rule> rules = Arrays.asList(new BulkBuyLimitCategoryRule("Paracetamol", 5));

        RuleEngine ruleEngine = new TescoRuleEngine(rules);


        RuleStatus ruleStatus = ruleEngine.fireRules(cart);

        System.out.println(" Rule status is = " + ruleStatus);


    }
}
