package com.example.banktest.services;

import com.example.banktest.entity.User;
import com.example.banktest.models.requests.AddPhoneRequestDto;
import com.example.banktest.models.requests.DeletePhoneRequestDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User getByPhoneOrEmail(@NonNull String usernameOrEmail);

    List<User> findUsersBySearchString(@NonNull String searchString, Pageable pageable);
}
