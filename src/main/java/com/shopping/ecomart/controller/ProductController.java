package com.shopping.ecomart.controller;

import com.shopping.ecomart.dtos.ProductDTO;
import com.shopping.ecomart.dtos.ResultResponseDTO;
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
    public ResultResponseDTO getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResultResponseDTO getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    private ResultResponseDTO addProduct(@RequestBody Product product)
    {
        return productService.addProduct(product);
    }
    @PutMapping("/products")
    private ResultResponseDTO updateProduct(@RequestBody ProductDTO productDTO)
    {
        return productService.updateProduct(productDTO);
    }
}
