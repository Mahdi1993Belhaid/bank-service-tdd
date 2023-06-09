package com.belhaid.mahdi.bankservicetdd.controllers;

import com.belhaid.mahdi.bankservicetdd.dto.RequestDisposeAmountDTO;
import com.belhaid.mahdi.bankservicetdd.dto.RequestWithdrawalDTO;
import com.belhaid.mahdi.bankservicetdd.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;



    @PostMapping("/debit")
    public ResponseEntity<Void> debitAccount(@RequestBody RequestWithdrawalDTO request) {
        accountService.debitAccount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dispose-amount")
    public ResponseEntity<Void> disposeAmount(@RequestBody RequestDisposeAmountDTO request) {
        accountService.disposeAmount(request);
        return ResponseEntity.ok().build();
    }
}
