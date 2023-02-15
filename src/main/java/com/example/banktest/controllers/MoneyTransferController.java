package com.example.banktest.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfers")
@Tag(name = "Money transfer management", description = "Allows you to send money between users accounts")
@RequiredArgsConstructor
public class MoneyTransferController {


}
