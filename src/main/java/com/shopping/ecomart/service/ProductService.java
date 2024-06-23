package com.shopping.ecomart.service;

import com.shopping.ecomart.entity.Product;
import com.shopping.ecomart.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepo;
    public List<Product> getAllProducts(){
        log.info("Returning all products");
        List<Product> productList = new ArrayList<>();
        Iterable<Product> allProducts = productRepo.findAll();
        allProducts.forEach(product -> productList.add(product));
        return productList;
    }
    public Product getProductById(int productId){
        log.info("returning product for Product Id: "+productId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
        return optionalProduct.isPresent()?optionalProduct.get():null;
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }
}
