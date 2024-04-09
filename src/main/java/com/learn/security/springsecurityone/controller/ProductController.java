package com.learn.security.springsecurityone.controller;

import com.learn.security.springsecurityone.entity.User;
import com.learn.security.springsecurityone.model.Product;
import com.learn.security.springsecurityone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome ! This endpoint is un-authenticated";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/products/{id}")
    public Product getProductBy(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/products/{id}")
    public String deleteProductBy(@PathVariable Long id) {
        if (productService.deleteProductBy(id)) {
            return "Product deleted successfully with id :" + id;
        } else {
            return "Product not found";
        }

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return productService.getAllUsers();
    }


}
