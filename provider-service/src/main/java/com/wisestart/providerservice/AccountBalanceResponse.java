package com.wisestart.providerservice;

import java.math.BigDecimal;

public record AccountBalanceResponse(String account, BigDecimal balance) {
}
