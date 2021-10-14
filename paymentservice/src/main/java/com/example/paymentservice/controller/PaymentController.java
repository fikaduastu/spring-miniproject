package com.example.paymentservice.controller;

import com.example.paymentservice.dto.CreditCardDto;
import com.example.paymentservice.dto.MasterCardDto;
import com.example.paymentservice.dto.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{type}")
    public PaymentDto payPayment(@PathVariable String type) {

        System.out.println("inside payment");
        PaymentDto paymentDto = new PaymentDto();
        if (type.equals("creditcard")){

            System.out.println("inside credit payment" +type);
           CreditCardDto creditcard =  restTemplate.getForObject("http://CREDITCARD-SERVICE/api/card/creditcard"
                    ,CreditCardDto.class);

           paymentDto.setStatus(creditcard.getStatus());
            System.out.println(paymentDto.getStatus());
           return paymentDto;
        }
         if(type.equals("mastercard")){
            System.out.println("mastercard");
            MasterCardDto mastercard = restTemplate.getForObject("http://MASTERCARD-SERVICE/api/"
                    ,MasterCardDto.class);
            paymentDto.setStatus(mastercard.getStatus());
            return paymentDto;
        }
        else
            return null;
    }
}
