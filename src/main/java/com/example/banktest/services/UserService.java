package com.example.banktest.services;

import com.example.banktest.entity.User;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User getByPhoneOrEmail(@NonNull String usernameOrEmail);

    User getByIdOrThrow(@NonNull Long userId);

    List<User> findUsersBySearchString(@NonNull String searchString, Pageable pageable);
}
