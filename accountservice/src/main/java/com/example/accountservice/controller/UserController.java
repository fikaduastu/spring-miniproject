package com.example.accountservice.controller;

import com.example.accountservice.dto.ResponseOrderWithProductDTO;
import com.example.accountservice.dto.ResponseUserWithOrderDTO;
import com.example.accountservice.entity.User;
import com.example.accountservice.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService2 userService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long userId){
        return userService.getUserById(userId);
    }

//    @GetMapping("/{id}/orders")
//    public ResponseUserWithOrderDTO getUserWithOrder(@PathVariable("id") long userId){
//        System.out.println("inside");
//        return userService.getUserWithOrder(userId);
//    }
    @GetMapping("/{userId}/orders/{orderId}/products")
    public ResponseOrderWithProductDTO getUserWithOrder(@PathVariable("userId") long userId, @PathVariable("orderId")long orderId){
        return userService.getOrderWithProduct(userId,orderId);
    }
}
