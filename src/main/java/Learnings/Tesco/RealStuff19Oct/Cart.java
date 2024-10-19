package Learnings.Tesco.RealStuff19Oct;


import java.util.List;

public class Cart {


    List<Product> products;

    public Cart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
