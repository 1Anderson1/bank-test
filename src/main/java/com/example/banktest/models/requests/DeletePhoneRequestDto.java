package com.example.banktest.models.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Validated
public class DeletePhoneRequestDto {

    @NotBlank
    @Size(min = 6, max = 13, message = "Phone length incorrect")
    @Digits(fraction = 0, integer = 13)
    private String phoneToDelete;
}
