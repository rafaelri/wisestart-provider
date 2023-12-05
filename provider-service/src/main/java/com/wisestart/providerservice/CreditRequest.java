package com.wisestart.providerservice;

import java.math.BigDecimal;

public record CreditRequest(String account, BigDecimal amount, String description) {
}
