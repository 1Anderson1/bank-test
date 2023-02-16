package com.example.banktest.controllers;

import com.example.banktest.models.requests.LoginRequestDto;
import com.example.banktest.security.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Users authorization")
@RequiredArgsConstructor
public class AuthorizationController {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Operation(summary = "Getting JWT token")
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid LoginRequestDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getPhoneOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenService.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
