package com.belhaid.mahdi.bankservicetdd.dto;

import java.math.BigDecimal;

public record RequestDisposeAmountDTO(
        String accountId,
        BigDecimal amount
) {
}
