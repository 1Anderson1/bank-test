package com.example.banktest.repository;

import com.example.banktest.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    boolean existsByPhone(String phone);

    void deleteByPhone(String phone);

    Phone getByPhone(String phone);
}
