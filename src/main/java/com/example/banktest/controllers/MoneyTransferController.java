package com.example.banktest.controllers;


import com.example.banktest.entity.User;
import com.example.banktest.models.requests.CreateMoneyTransferRequestDto;
import com.example.banktest.services.UserAccountService;
import com.example.banktest.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/transfers")
@Tag(name = "Money transfer management", description = "Allows you to send money between users accounts")
@RequiredArgsConstructor
public class MoneyTransferController {

    private final UserAccountService userAccountService;
    private final UserService userService;

    @Operation(summary = "Transfer money")
    @PostMapping()
    public void sendMoney(@RequestBody @Valid CreateMoneyTransferRequestDto createMoneyTransferRequestDto,
                          @Parameter(hidden = true)
                          @AuthenticationPrincipal UserDetails userDetails) {
        User sender = userService.getByPhoneOrEmail(userDetails.getUsername());
        User recipient = userService.getByIdOrThrow(createMoneyTransferRequestDto.getRecipientUserId());
        userAccountService.transferMoney(sender.getAccount().getId(),
                recipient.getAccount().getId(),
                createMoneyTransferRequestDto.getAmount());
    }
}
