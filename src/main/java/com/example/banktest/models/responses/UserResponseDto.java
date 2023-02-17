package com.example.banktest.models.responses;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserResponseDto {

    @NotNull
    private Long id;
}
