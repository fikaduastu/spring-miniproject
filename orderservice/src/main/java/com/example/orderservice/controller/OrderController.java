package com.example.orderservice.controller;

import com.example.orderservice.dto.ResponseOrderWithProductDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public Order saveOrder(@RequestBody Order order){

        return orderService.saveOrder(order);
    }

//    @GetMapping()
//    public List<Order> getAllOrders(){
//        return orderService.getAllOrders();
//    }


    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id){
        return orderService.getOrderById(id);
    }


    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersForUser(@PathVariable("userId") long userId){
        return orderService.getOrderByUserId(userId);
    }


    @GetMapping("/{userId}/orders/{productId}")
    public ResponseOrderWithProductDTO getOrderWithProduct(@PathVariable("userId") long userId, @PathVariable("productId") long productId){
        return orderService.getOrderWithProduct(userId, productId);
    }


}
