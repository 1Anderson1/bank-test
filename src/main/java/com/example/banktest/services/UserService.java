package com.example.banktest.services;

import com.example.banktest.entity.User;

public interface UserService {

    User getByPhoneOrEmail(String usernameOrEmail);
}
