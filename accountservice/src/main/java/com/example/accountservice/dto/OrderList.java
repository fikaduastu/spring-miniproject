package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class OrderList {
    private List<Order> orders;

    public OrderList(){
        orders = new ArrayList<>();
    }
}
