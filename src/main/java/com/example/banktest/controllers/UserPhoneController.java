package com.example.banktest.controllers;

import com.example.banktest.entity.Phone;
import com.example.banktest.mappers.EntityMapper;
import com.example.banktest.models.requests.AddPhoneRequestDto;
import com.example.banktest.models.requests.DeletePhoneRequestDto;
import com.example.banktest.models.requests.UpdatePhoneRequestDto;
import com.example.banktest.models.responses.PhoneResponseDto;
import com.example.banktest.services.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/phone")
@Tag(name = "Phone info management")
@RequiredArgsConstructor
public class UserPhoneController {

    private final PhoneService phoneService;
    private final EntityMapper entityMapper;

    @Operation(summary = "Add phone number to user")
    @PostMapping()
    public PhoneResponseDto addPhone(@RequestBody @Valid AddPhoneRequestDto addPhoneRequestDto,
                                     @Parameter(hidden = true)
                                     @AuthenticationPrincipal UserDetails userDetails) {
        Phone phone = phoneService.addUserPhone(addPhoneRequestDto, userDetails.getUsername());
        return entityMapper.phoneToPhoneResponseDto(phone);
    }

    @Operation(summary = "Delete user phone number")
    @DeleteMapping()
    public void deletePhone(@RequestBody @Valid DeletePhoneRequestDto deletePhoneRequestDto,
                            @Parameter(hidden = true)
                            @AuthenticationPrincipal UserDetails userDetails) {
        phoneService.deleteUserPhone(deletePhoneRequestDto, userDetails.getUsername());
    }

    @Operation(summary = "Update user phone number")
    @PutMapping()
    public PhoneResponseDto updatePhone(@RequestBody @Valid UpdatePhoneRequestDto updatePhoneRequestDto,
                                        @Parameter(hidden = true)
                                        @AuthenticationPrincipal UserDetails userDetails) {
        Phone phone = phoneService.updatePhone(updatePhoneRequestDto, userDetails.getUsername());
        return entityMapper.phoneToPhoneResponseDto(phone);
    }
}
