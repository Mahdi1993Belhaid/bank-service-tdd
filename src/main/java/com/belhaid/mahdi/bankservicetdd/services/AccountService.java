package com.belhaid.mahdi.bankservicetdd.services;

import com.belhaid.mahdi.bankservicetdd.dto.RequestDisposeAmountDTO;
import com.belhaid.mahdi.bankservicetdd.dto.RequestWithdrawalDTO;
import com.belhaid.mahdi.bankservicetdd.entities.Account;
import com.belhaid.mahdi.bankservicetdd.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    @Value(value = "limit.max")
    private BigDecimal max;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void debitAccount(RequestWithdrawalDTO request) {
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(request.amount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        if (max.compareTo(request.amount()) > 0) {
            throw new RuntimeException("the amount of the operation heigher than limit");
        }
        BigDecimal updatedBalance = currentBalance.subtract(request.amount());
        account.setBalance(updatedBalance);
        accountRepository.save(account);
    }

    public void disposeAmount(RequestDisposeAmountDTO request) {

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        account.setBalance(account.getBalance().add(request.amount()));

        accountRepository.save(account);
    }
}
