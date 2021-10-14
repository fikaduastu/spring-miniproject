package com.example.productservice.dto;

import com.example.productservice.entity.Product;
import lombok.Data;

@Data
public class Response {
    public Account account;
    public Product product;
}
