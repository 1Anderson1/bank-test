package com.example.banktest.repository;

import com.example.banktest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhonesPhone(String phone);

    Optional<User> findByEmailsEmail(String email);
}
