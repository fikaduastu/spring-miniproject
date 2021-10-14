package com.example.accountservice.dto;

import com.example.accountservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserWithOrderDTO {
    private User user;
    private List<Order> order;
//    private Product product;

}
