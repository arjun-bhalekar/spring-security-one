package com.learn.security.springsecurityone.service;

import com.learn.security.springsecurityone.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(101L, "Dell Keyboard 101", "Dell Wireless Standard keyboard"));
        productList.add(new Product(102L, "Dell Mouse 102", "Dell Wireless Standard Mouse"));
        productList.add(new Product(103L, "Dell Monitor", "Dell Standard Size LED Monitor"));
        productList.add(new Product(104L, "Dell i5 Laptop", "Dell Standard I5 Laptop"));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(Long id) {

        return productList
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteProductBy(Long id) {
        return productList.remove(getProductById(id));
    }
}
