package com.shopping.ecomart.service;

import com.shopping.ecomart.dtos.ProductDTO;
import com.shopping.ecomart.entity.Product;
import com.shopping.ecomart.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepo;
    public Response<List<Product>> getAllProducts(){
        log.info("Returning all Products");
        List<Product> productList = new ArrayList<>();
        Iterable<Product> allProducts = productRepo.findAll();
        allProducts.forEach(productList::add);
        return new Response<List<Product>>("DATA FOUND",productList);
    }
    public Response<Product> getProductById(int productId){
        log.info("returning product for Product Id: "+productId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
       return new Response<>("DATA FOUND", optionalProduct.orElse(null));
    }
    public Response<ProductDTO> updateProduct(ProductDTO productDTO){
        Product productEntity = new Product();
        BeanUtils.copyProperties(productDTO,productEntity);
        int productId = productDTO.getProductId();
        Optional<Product> optionalProduct = productRepo.findById(productId);
        Product productEntityNew = optionalProduct.orElse(null);
        if (productEntityNew == null) {
            return new Response<>("INVALID INPUT DATA  ", null);
        }
        log.info("updating product for Product Id: "+productId);
        productEntity = productRepo.save(productEntity);
        BeanUtils.copyProperties(productEntity,productDTO);
        return new Response<>("DATA UPDATED",productDTO);
    }

    public Response<Product> addProduct(Product product){
        Product savedProduct = productRepo.save(product);
        return new Response<>("CREATED", savedProduct);

    }
}
