package com.example.banktest.scheduled;

import com.example.banktest.entity.Account;
import com.example.banktest.services.UserAccountService;
import com.example.banktest.utils.PercentageMathUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BalanceScheduler {

    private final static HashMap<Long, BigDecimal> accountsMaxLimit = new HashMap<>();
    private final BigDecimal maxPercentage = new BigDecimal(207);
    private final UserAccountService userAccountService;

    @Scheduled(fixedDelay = 30000, initialDelay = 30000)
    public void increaseUserBalance() {
        List<Account> accountsWithPositiveBalance = userAccountService
                .getAllUserAccounts()
                .stream()
                .filter(account -> !account.getBalance().equals(BigDecimal.ZERO))
                .collect(Collectors.toList());

        accountsWithPositiveBalance.forEach(account ->
                accountsMaxLimit.putIfAbsent(account.getUserId(),
                        PercentageMathUtil.getIncreasedByPercentage(account.getBalance(), maxPercentage)));

        accountsWithPositiveBalance
                .parallelStream()
                .forEach(account -> userAccountService.increaseBalanceBy10Percent(account.getId(), accountsMaxLimit.get(account.getId())));
    }
}
