package com.wisestart.providerservice;

import java.math.BigDecimal;

public record OpenAccountRequest(String account, BigDecimal initialBalance) {
}
