package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public Product getProductByUserId(long userId) {
        return productRepository.getProductByUserId(userId);
    }
}
