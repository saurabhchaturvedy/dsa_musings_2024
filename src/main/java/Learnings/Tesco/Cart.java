package Learnings.Tesco;

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
