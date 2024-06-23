package com.shopping.ecomart.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T> {

 private String message;
 private T data;

}