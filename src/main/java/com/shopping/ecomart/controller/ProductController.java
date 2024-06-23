package com.shopping.ecomart.controller;

import com.shopping.ecomart.entity.Product;
import com.shopping.ecomart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    private void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }
}
