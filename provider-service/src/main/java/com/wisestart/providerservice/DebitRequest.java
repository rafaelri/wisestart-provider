package com.wisestart.providerservice;

import java.math.BigDecimal;

public record DebitRequest(String account, BigDecimal amount, String description) {
}
