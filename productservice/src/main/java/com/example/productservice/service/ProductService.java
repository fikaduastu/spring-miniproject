package com.example.productservice.service;

import com.example.productservice.entity.Product;

public interface ProductService {
    Product getProductById(long productId);

    Product getProductByUserId(long userId);
}
