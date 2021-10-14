package com.example.mastercardservice.controller;

import com.example.mastercardservice.dto.MasterCardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MasterCardController {

    MasterCardDto masterCardDto = new MasterCardDto();
    @GetMapping("/")
    public MasterCardDto pay(){
        masterCardDto.setStatus("you payed the payment using mastercard");
        return masterCardDto;
    }
}
