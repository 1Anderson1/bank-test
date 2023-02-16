package com.example.banktest.controllers;

import com.example.banktest.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "User info management")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
