package Learnings.Tesco.Pre;

public class Product {

    int productId;
    String category;
    int quantity;

    public Product(int productId, String category, int quantity) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    int getQuantity() {
        return quantity;
    }
}
