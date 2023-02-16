package com.example.banktest.services;

import com.example.banktest.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface UserAccountService {

    List<Account> getAllUserAccounts();

    void increaseBalanceBy10Percent(Long accountId, BigDecimal maxBalance);
}
