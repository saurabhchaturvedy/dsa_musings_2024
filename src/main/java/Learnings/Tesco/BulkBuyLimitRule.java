package Learnings.Tesco;

public class BulkBuyLimitRule implements Rule {

    int totalQuantity;


    BulkBuyLimitRule(int totalQuantity) {

        this.totalQuantity = totalQuantity;
    }

    @Override
    public boolean applyRule(Cart cart) {
        int quantity = cart.products.stream().mapToInt(Product::getQuantity).sum();
        return quantity<=this.totalQuantity;
    }



    @Override
    public String getName() {
        return "BulkBuyLimitRule";
    }
}
