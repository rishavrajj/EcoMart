package com.shopping.ecomart.service;

import com.shopping.ecomart.dtos.ProductDTO;
import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.entity.Product;
import com.shopping.ecomart.exception.ResourceAlreadyExistException;
import com.shopping.ecomart.exception.ResourceNotFoundException;
import com.shopping.ecomart.repository.ProductRepository;
import com.shopping.ecomart.util.ApplicationConstant;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.shopping.ecomart.util.ApplicationConstant.StatusCode;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepo;
    @PreAuthorize("hasAnyRole('user','admin')")
    public ResultResponseDTO getAllProducts() {
        log.info("Returning all Products");
        List<ProductDTO> productList = new ArrayList<>();
        Iterable<Product> allProducts = productRepo.findAll();
        if (!allProducts.iterator().hasNext()) {
            return ResultResponseDTO.builder().message(StatusCode.DATA_NOT_FOUND).key(ApplicationConstant.FAILURE).data(productList).build();
        }
        allProducts.forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            productList.add(productDTO);
        });
        return ResultResponseDTO.builder().message(StatusCode.DATA_FOUND).key(ApplicationConstant.SUCCESS).data(productList).build();
    }
    @PreAuthorize("hasAnyRole('admin','user')")
    public ResultResponseDTO getProductById(int productId) {
        log.info("Returning product for Product Id: " + productId);
        Optional<Product> optionalProduct = productRepo.findById(productId);
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId)), productDTO);
        return ResultResponseDTO.builder().message(StatusCode.DATA_FOUND).key(ApplicationConstant.SUCCESS).data(productDTO).build();
    }
    @Transactional
    @PreAuthorize("hasRole('admin')")
    public ResultResponseDTO updateProduct(ProductDTO productDTO) {
        int productId = productDTO.getId();
        Optional<Product> optionalProduct = productRepo.findById(productId);
        Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        BeanUtils.copyProperties(productDTO,product);
        log.info("Updating product for Product Id: " + productId);
        Product productEntity = productRepo.save(product);
        BeanUtils.copyProperties(productEntity, productDTO);
        return ResultResponseDTO.builder().message(StatusCode.RESOURCE_UPDATED).key(ApplicationConstant.SUCCESS).data(productDTO).build();
    }
    @Transactional
    @PreAuthorize("hasRole('admin')")
    public ResultResponseDTO addProduct(Product product) {
        List<Product> pr = productRepo.findByProductName(product.getProductName());
        if (!pr.isEmpty()) {
            throw new ResourceAlreadyExistException("Product", "ProductName", product.getProductName());
        }
        Product savedProduct = productRepo.save(product);
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(savedProduct, productDTO);
        return ResultResponseDTO.builder().message(StatusCode.RESOURCE_CREATED).key(ApplicationConstant.SUCCESS).data(productDTO).build();
    }
    @Transactional
    @PreAuthorize("hasRole('admin')")
    public ResultResponseDTO deleteProduct(int productId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        Product productEntity = optionalProduct.orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        log.info("Deleting Product for Product Id: " + productId);
        productRepo.delete(productEntity);
        return ResultResponseDTO.builder().message(StatusCode.RESOURCE_DELETED).key(ApplicationConstant.SUCCESS).data("Product deleted for Product Id:" + productId)
                .data(null).build();
    }
}
