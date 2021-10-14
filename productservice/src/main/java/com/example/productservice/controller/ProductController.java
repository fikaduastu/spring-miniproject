package com.example.productservice.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Data
    class SendId{
        long id;
    }
    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable long id){
        return productRepository.findById(id).orElse(null);
    }

    //=================================================================

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }


//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable("id") long productId){
//        return productService.getProductById(productId);
//    }
//    @GetMapping("/{userId}/product")
//    public Product getProductForUser(@PathVariable("userId") long userId){
//        return productService.getProductByUserId(userId);
//    }

    //====================================================

    @PostMapping("/{id}")
    public Product save(@PathVariable long id,@RequestBody Product product){
        product.setUserId(id);
        return productRepository.save(product);
    }
    @GetMapping("/{id}")
    public List<Product> getProductsByAccountId(@PathVariable long id){
        return   productRepository.findByUserId(id);
    }

}
