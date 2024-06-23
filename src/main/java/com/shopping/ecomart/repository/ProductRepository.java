package com.shopping.ecomart.repository;

import com.shopping.ecomart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
