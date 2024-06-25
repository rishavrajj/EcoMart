package com.shopping.ecomart.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class ProductBuilder {
    private ProductBuilder() {
    }

    ;

    public static class Builder {

        private Builder(){}
        private int productId;
        private String productName;
        private int price;
        private String productDesc;
        private int quantity;
        private String category;
        private LocalDateTime createdTime;
        private LocalDateTime updatedTime;


        public Builder setProductId(int productId) {
            this.productId = productId;
            return this;
        }


        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }


        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }


        public Builder setProductDesc(String productDesc) {
            this.productDesc = productDesc;
            return this;
        }


        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }


        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }


        public Builder setCreatedTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
            return this;
        }


        public Builder setUpdatedTime(LocalDateTime updatedTime) {
            this.updatedTime = updatedTime;
            return this;
        }

        public Product build(){
            Product product = new Product();
//            product.setProductId(productId);
            product.setProductName(productName);
            product.setPrice(price);
            product.setProductDesc(productDesc);
            product.setQuantity(quantity);
            product.setCategory(category);
//            product.setCreatedTime(createdTime);
//            product.setUpdatedTime(updatedTime);
            return product;
        }

    }
    public static Builder builder(){
        return new ProductBuilder.Builder();
    }
}
