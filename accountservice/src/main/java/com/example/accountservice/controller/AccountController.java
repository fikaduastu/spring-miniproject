package com.example.accountservice.controller;


import com.example.accountservice.dto.*;
import com.example.accountservice.entity.Account;
import com.example.accountservice.entity.User;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.repository.UserRepo;
import com.example.accountservice.service.UserService2;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService2 userService;


    private final AccountRepository accountRepository;
    @PostMapping("/signup")
    public Account signup(@RequestBody Account account){
        return accountRepository.save(account);
    }

    @GetMapping("/hello")
    public String login(@RequestBody Account account){
        return "hello";

    }

    @GetMapping("/{id}")
    public Account getUser(@PathVariable long id){
        return accountRepository.findById(id).orElse(null);
    }


    @GetMapping("/{id}/products/{id2}/payments/{type}")
    public String getProduct(@PathVariable long id,@PathVariable long id2,@PathVariable String type){
        System.out.println("inside account sercvice");
        User account =  userRepo.findById(id).get();

        ResponseEntity<Product> product =
                restTemplate.getForEntity("http://PRODUCT-SERVICE/products/getProduct/" + id2,Product.class);

            if ((type.equals("creditcard") || type.equals("mastercard") && product != null && account != null)){
                Order order = new Order();
                order.setProductId(id2);
                order.setUserId(id);
                ResponseEntity<Order> order1 = restTemplate.postForEntity("http://ORDER-SERVICE/api/",order,Order.class);
                return "order placed by "+type;
            }

                return "order placement error";


    }

    //=======================
    @GetMapping("/{id}/products")
    public Response getUserWithProducts(@PathVariable long id) {

        Response response = new Response();
        Account account = accountRepository.findById(id).get();
        ResponseEntity<Product[]> responsee =
                restTemplate.getForEntity(
                        "http://PRODUCT-SERVICE/api/products/" + account.getID(),
                        Product[].class);
        Product[] products = responsee.getBody();
        assert products != null;
        List<Product> listP = Arrays.stream(products).collect(Collectors.toList());
        response.setAccount(account);
        response.setProduct(listP);
        return response;
    }

    @PostMapping("/{id}/products")
    public Response2 postProductWithUser(@PathVariable("id") long id, @RequestBody Product product) {
        Response2 response = new Response2();

        System.out.println(product.getProductName());
        User user = userRepo.findById(id).get();
        Product product1=restTemplate.postForObject("http://PRODUCT-SERVICE/products/"
                +id,product,Product.class);
        assert product1 != null;
        response.setProduct(product1);
        return response;
    }
    @GetMapping("/{id}/orders")
    public ResponseUserWithOrderDTO getUserWithOrder(@PathVariable("id") long userId){
        return userService.getUserWithOrder(userId);
    }
    @GetMapping("/{userId}/orders/{orderId}/products")
    public ResponseOrderWithProductDTO getUserWithOrder(@PathVariable("userId") long userId, @PathVariable("orderId")long orderId){
        return userService.getOrderWithProduct(userId,orderId);
    }
}
