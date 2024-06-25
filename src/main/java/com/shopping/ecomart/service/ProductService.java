package com.shopping.ecomart.service;

import com.shopping.ecomart.dtos.ProductDTO;
import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.entity.Product;
import com.shopping.ecomart.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    public ResultResponseDTO getAllProducts(){
        log.info("Returning all Products");
        List<Product> productList = new ArrayList<>();
        Iterable<Product> allProducts = productRepo.findAll();
        allProducts.forEach(productList::add);
        return new ResultResponseDTO("DATA FOUND",productList);
    }
    public ResultResponseDTO getProductById(int productId){
        log.info("returning product for Product Id: "+productId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
       return new ResultResponseDTO("DATA FOUND", optionalProduct.orElse(null));
    }
    public ResultResponseDTO updateProduct(ProductDTO productDTO){
        Product productEntity = new Product();
        BeanUtils.copyProperties(productDTO,productEntity);
        int productId = productDTO.getProductId();
        Optional<Product> optionalProduct = productRepo.findById(productId);
        Product productEntityNew = optionalProduct.orElse(null);
        if (productEntityNew == null) {
            return new ResultResponseDTO("INVALID INPUT DATA  ", null);
        }
        log.info("updating product for Product Id: "+productId);
        productEntity = productRepo.save(productEntity);
        BeanUtils.copyProperties(productEntity,productDTO);
        return new ResultResponseDTO("DATA UPDATED",productDTO);
    }

    public ResultResponseDTO addProduct(Product product){
        Product savedProduct = productRepo.save(product);
        return new ResultResponseDTO("CREATED", savedProduct);


    }
}
