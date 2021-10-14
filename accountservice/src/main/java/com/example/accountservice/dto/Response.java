package com.example.accountservice.dto;

import com.example.accountservice.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    public Account account;
    public List<Product> product;
}
