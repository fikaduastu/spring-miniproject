package com.example.accountservice.service;

import com.example.accountservice.dto.ResponseOrderWithProductDTO;
import com.example.accountservice.dto.ResponseUserWithOrderDTO;
import com.example.accountservice.entity.User;

public interface UserService2 {
    public User getUserById(long userId);
    public User saveUser(User user);

    ResponseUserWithOrderDTO getUserWithOrder(long userId);

    ResponseOrderWithProductDTO getOrderWithProduct(long userId, long orderId);
}
