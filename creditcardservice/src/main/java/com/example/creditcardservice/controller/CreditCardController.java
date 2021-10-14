package com.example.creditcardservice.controller;

import com.example.creditcardservice.dto.CreditCardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CreditCardController {

//    CreditCardDto creditCardDto = new CreditCardDto();

    @GetMapping("/creditcard")
    public CreditCardDto pay(){
        CreditCardDto creditCardDto = new CreditCardDto();
        creditCardDto.setID(1);
        creditCardDto.setStatus("PayedByCredit");
        System.out.println("inside credit service");
        return creditCardDto;
    }
}
