package com.example.banktest.services;

import com.example.banktest.entity.Account;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public interface UserAccountService {

    List<Account> getAllUserAccounts();

    void increaseBalanceBy10Percent(@NonNull Long accountId, @NonNull BigDecimal maxBalance);

    void transferMoney(@NonNull Long senderAccountId, @NonNull Long recipientAccountId, @NonNull BigDecimal amount);
}
