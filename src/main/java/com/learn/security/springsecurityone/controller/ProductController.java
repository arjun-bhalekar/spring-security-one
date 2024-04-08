package com.learn.security.springsecurityone.controller;

import com.learn.security.springsecurityone.model.Product;
import com.learn.security.springsecurityone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductBy(@PathVariable Long id) {

        return productService.getProductById(id);
    }


    @DeleteMapping("/products/{id}")
    public String deleteProductBy(@PathVariable Long id) {
        if(productService.deleteProductBy(id)) {
            return  "Product deleted successfully with id :"+id;
        } else {
            return "Product not found";
        }

    }



}
