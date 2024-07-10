package com.shopping.ecomart.dtos;

import lombok.Data;

@Data
public class ProductDTO {

    private int id;
    private String productName;
    private int price;
    private String productDesc;
    private int quantity;
    private String category;
}
