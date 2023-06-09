package com.belhaid.mahdi.bankservicetdd.services;

import com.belhaid.mahdi.bankservicetdd.controllers.AccountController;
import com.belhaid.mahdi.bankservicetdd.dto.RequestDisposeAmountDTO;
import com.belhaid.mahdi.bankservicetdd.dto.RequestWithdrawalDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BankServiceTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    public void testDebitAccount() {
        String accountId = UUID.randomUUID().toString();
        BigDecimal amount = BigDecimal.valueOf(100);
        RequestWithdrawalDTO request = new RequestWithdrawalDTO(accountId,amount);

        // Act
        ResponseEntity<Void> response = accountController.debitAccount(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService, times(1)).debitAccount(request);
    }
    @Test
    public void testDebitAccount_InsufficientBalance() {
        // Arrange
        String accountId = "account1";
        BigDecimal amount = BigDecimal.valueOf(100);
        RequestWithdrawalDTO request = new RequestWithdrawalDTO(accountId, amount);

        // Mock the service method to throw an exception
        doThrow(new IllegalArgumentException("Insufficient balance"))
                .when(accountService).debitAccount(request);

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> accountController.debitAccount(request));
        verify(accountService, times(1)).debitAccount(request);
    }

    @Test
    public void testDebitAccount_ExceedWithdrawalLimit() {
        // Arrange
        String accountId = "account1";
        BigDecimal amount = BigDecimal.valueOf(100);
        RequestWithdrawalDTO request = new RequestWithdrawalDTO(accountId, amount);

        // Mock the service method to throw an exception
        doThrow(new RuntimeException("the amount of the operation heigher than limit"))
                .when(accountService).debitAccount(request);

        // Act and Assert
        assertThrows(ResponseStatusException.class, () -> accountController.debitAccount(request));
        verify(accountService, times(1)).debitAccount(request);
    }
    @Test
    public void testDisposeAmount() {
        // Arrange
        String accountId = "account1";
        BigDecimal amount = BigDecimal.valueOf(100);
        RequestDisposeAmountDTO request = new RequestDisposeAmountDTO(accountId, amount);

        // Act
        ResponseEntity<Void> response = accountController.disposeAmount(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService, times(1)).disposeAmount(request);
    }

}