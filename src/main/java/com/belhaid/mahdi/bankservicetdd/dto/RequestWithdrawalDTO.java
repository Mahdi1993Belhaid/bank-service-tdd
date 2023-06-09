package com.belhaid.mahdi.bankservicetdd.dto;

import java.math.BigDecimal;

public record RequestWithdrawalDTO(
        String accountId,
        BigDecimal amount
) {
}
