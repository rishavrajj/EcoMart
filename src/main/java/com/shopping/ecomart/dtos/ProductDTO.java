package com.shopping.ecomart.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class ProductDTO {

    private int id;
    private String productName;
    private int price;
    private String productDesc;
    private int quantity;
    private String category;
}
