package Learnings.Tesco.RealStuff19Oct;

public class Product {


    public int productId;
    public String category;
    public int quantity;

    public Product(int productId, String category, int quantity) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }
}
