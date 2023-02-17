package com.example.banktest.services.impl;

import com.example.banktest.entity.Account;
import com.example.banktest.repository.AccountRepository;
import com.example.banktest.services.UserAccountService;
import com.example.banktest.utils.PercentageMathUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAllUserAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void increaseBalanceBy10Percent(@NonNull Long accountId, @NonNull BigDecimal maxBalance) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            return;
        }
        Account account = optionalAccount.get();
        BigDecimal increasedByPercentage = PercentageMathUtil.getIncreasedByPercentage(account.getBalance(), new BigDecimal(10));
        if (account.getBalance().compareTo(maxBalance) > 0 || increasedByPercentage.compareTo(maxBalance) > 0) {
            return;
        }
        account.setBalance(increasedByPercentage);
        save(account);
    }

    @Override
    @Transactional(timeout = 10)
    public void transferMoney(@NonNull Long senderAccountId, @NonNull Long recipientAccountId, @NonNull BigDecimal amount) {
        Optional<Account> senderAccountOptional = accountRepository.findAccountById(senderAccountId);
        Optional<Account> recipientAccountOptional = accountRepository.findAccountById(recipientAccountId);

        if (senderAccountId.equals(recipientAccountId)
                || senderAccountOptional.isEmpty()
                || recipientAccountOptional.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Account senderAccount = senderAccountOptional.get();
        Account recipientAccount = recipientAccountOptional.get();

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException();
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

        save(senderAccount);
        save(recipientAccount);
    }

    private Account save(Account account) {
        return accountRepository.save(account);
    }


}
