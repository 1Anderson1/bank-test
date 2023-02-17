package com.example.banktest.services.impl;


import com.example.banktest.entity.User;
import com.example.banktest.exceptions.NotFoundException;
import com.example.banktest.repository.UserRepository;
import com.example.banktest.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getByPhoneOrEmail(@NonNull String phoneOrEmail) {
        if (phoneOrEmail.matches("[0-9]+")) {
            return getByPhoneOrThrow(phoneOrEmail);
        } else {
            return getByEmailOrThrow(phoneOrEmail);
        }
    }

    @Override
    public User getByIdOrThrow(@NonNull Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> findUsersBySearchString(@NonNull String searchString, Pageable pageable) {
        return userRepository.findAllBySearchQuery(searchString, pageable.getPageNumber(), pageable.getPageSize());
    }

    private User getByPhoneOrThrow(String phone) {
        Optional<User> userOptional = userRepository.findByPhonesPhone(phone);
        return userOptional.orElseThrow(NotFoundException::new);
    }

    private User getByEmailOrThrow(String email) {
        Optional<User> userOptional = userRepository.findByEmailsEmail(email);
        return userOptional.orElseThrow(NotFoundException::new);
    }
}
