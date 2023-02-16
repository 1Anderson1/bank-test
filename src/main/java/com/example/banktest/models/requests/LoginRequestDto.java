package com.example.banktest.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Validated
public class LoginRequestDto {

    @NotBlank
    @Schema(description = "Phone ore Email for login")
    @Size(min = 3, max = 200, message = "Incorrect string length")
    private String phoneOrEmail;

    @NotBlank
    @Schema(description = "Password for login")
    @Size(min = 8, max = 500, message = "Incorrect string length")
    private String password;
}
