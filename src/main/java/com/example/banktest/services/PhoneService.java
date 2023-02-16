package com.example.banktest.services;

import com.example.banktest.entity.Phone;
import com.example.banktest.models.requests.AddPhoneRequestDto;
import com.example.banktest.models.requests.DeletePhoneRequestDto;
import com.example.banktest.models.requests.UpdatePhoneRequestDto;
import lombok.NonNull;

public interface PhoneService {

    boolean isPhoneExists(@NonNull String phone);

    Phone addUserPhone(@NonNull AddPhoneRequestDto addPhoneRequestDto, @NonNull String emailOrPhone);

    void deleteUserPhone(@NonNull DeletePhoneRequestDto deletePhoneRequestDto, @NonNull String emailOrPhone);

    Phone updatePhone(@NonNull UpdatePhoneRequestDto updatePhoneRequestDto, @NonNull String emailOrPhone);
}
