package Learnings.Tesco.RealStuff19Oct;

public class BulkBuyLimitCategoryRule implements Rule {


    String category;
    int quantityLimit;

    public BulkBuyLimitCategoryRule(String category, int quantityLimit) {
        this.category = category;
        this.quantityLimit = quantityLimit;
    }

    @Override
    public boolean applyRule(Cart cart) {

        int currentCategoryQuantity = cart.getProducts().stream().filter(product -> product.getCategory().equalsIgnoreCase(category)).mapToInt(Product::getQuantity).sum();
        return currentCategoryQuantity <= quantityLimit;
    }
}
