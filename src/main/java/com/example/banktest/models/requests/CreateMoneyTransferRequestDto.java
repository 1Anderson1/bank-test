package com.example.banktest.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Validated
@Data
public class CreateMoneyTransferRequestDto {

    @NotNull
    @PositiveOrZero
    @Schema(description = "Recipient user id")
    private Long recipientUserId;

    @NotNull
    @Positive
    @Schema(description = "The amount of money transferred must be greater than zero")
    private BigDecimal amount;
}
