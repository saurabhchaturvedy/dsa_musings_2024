package Learnings.Tesco.Pre;

import Learnings.Tesco.RealStuff19Oct.Cart;

public class BulkBuyCategoryQuantityLimitRule implements Rule {

    int totalQuantityLimit;
    String category;

    public BulkBuyCategoryQuantityLimitRule(int totalQuantityLimit, String category) {
        this.totalQuantityLimit = totalQuantityLimit;
        this.category = category;
    }

    @Override
    public boolean applyRule(Cart cart) {
        //   int quantity = cart.getProducts().stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).mapToInt(Product::getQuantity).sum();
        //  return quantity <= totalQuantityLimit;
        return true;
    }

    @Override
    public String getName() {
        return "BulkBuyCategoryQuantityLimitRule";
    }
}
