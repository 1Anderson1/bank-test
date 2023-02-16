package com.example.banktest.services;

import com.example.banktest.entity.User;
import com.example.banktest.models.requests.AddPhoneRequestDto;
import com.example.banktest.models.requests.DeletePhoneRequestDto;

public interface UserService {

    User getByPhoneOrEmail(String usernameOrEmail);

    User save(User user);
}
