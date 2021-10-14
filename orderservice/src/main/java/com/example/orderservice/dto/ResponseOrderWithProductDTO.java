package com.example.orderservice.dto;

import com.example.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderWithProductDTO {
    private Order order;
    private Product product;
}
