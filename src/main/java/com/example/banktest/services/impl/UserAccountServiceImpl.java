package com.example.banktest.services.impl;

import com.example.banktest.entity.Account;
import com.example.banktest.repository.AccountRepository;
import com.example.banktest.services.UserAccountService;
import com.example.banktest.utils.PercentageMathUtil;
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
    public void increaseBalanceBy10Percent(Long accountId, BigDecimal maxBalance) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()){
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

    private Account save(Account account) {
        return accountRepository.save(account);
    }


}
