package com.example.banktest;

import com.example.banktest.entity.Account;
import com.example.banktest.services.UserAccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BankTestApplicationTests {

    @Autowired
    UserAccountService userAccountService;

    @Test
    void contextLoads() {
    }

    @Test
    void moneyTransfer() {

        Map<Long, BigDecimal> balancesBefore = getAccountBalances();
        BigDecimal senderBalanceBeforeTransaction = balancesBefore.get(1L);
        BigDecimal recipientBalanceBeforeTransaction = balancesBefore.get(2L);

        userAccountService.transferMoney(1L, 2L, BigDecimal.valueOf(1));

        Map<Long, BigDecimal> balancesAfter = getAccountBalances();
        BigDecimal senderBalancesAfterTransaction = balancesAfter.get(1L);
        BigDecimal recipientBalancesAfterTransaction = balancesAfter.get(2L);

        Assertions.assertThat(senderBalanceBeforeTransaction)
                .isEqualByComparingTo(senderBalancesAfterTransaction.add(BigDecimal.valueOf(1)));

        Assertions.assertThat(recipientBalanceBeforeTransaction)
                .isEqualByComparingTo(recipientBalancesAfterTransaction.subtract(BigDecimal.valueOf(1)));
    }

    @Test
    void moneyTransferConcurrent() throws InterruptedException {
        Map<Long, BigDecimal> balancesBefore = getAccountBalances();
        BigDecimal senderBalanceBeforeTransaction = balancesBefore.get(1L);
        BigDecimal recipientBalanceBeforeTransaction = balancesBefore.get(2L);

        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                userAccountService.transferMoney(1L, 2L, BigDecimal.valueOf(1));
            });
        }
        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        Map<Long, BigDecimal> balancesAfter = getAccountBalances();
        BigDecimal senderBalancesAfterTransaction = balancesAfter.get(1L);
        BigDecimal recipientBalancesAfterTransaction = balancesAfter.get(2L);

        Assertions.assertThat(senderBalanceBeforeTransaction)
                .isEqualByComparingTo(senderBalancesAfterTransaction.add(BigDecimal.valueOf(100)));

        Assertions.assertThat(recipientBalanceBeforeTransaction)
                .isEqualByComparingTo(recipientBalancesAfterTransaction.subtract(BigDecimal.valueOf(100)));
    }


    private Map<Long, BigDecimal> getAccountBalances() {
        List<Account> allUserAccounts = userAccountService.getAllUserAccounts();
        Account sender = allUserAccounts.stream().filter(account -> account.getId().equals(1L)).findFirst().get();
        Account recipient = allUserAccounts.stream().filter(account -> account.getId().equals(2L)).findFirst().get();
        Map<Long, BigDecimal> balances = new HashMap<>();
        balances.put(sender.getId(), sender.getBalance());
        balances.put(recipient.getId(), recipient.getBalance());
        return balances;
    }
}
