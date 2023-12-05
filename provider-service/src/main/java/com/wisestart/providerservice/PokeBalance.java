package com.wisestart.providerservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/balance")
public class PokeBalance {
    private final Map<String, BigDecimal> balances = new HashMap<>();

    @PostMapping("/accounts")
    public void open(@RequestBody OpenAccountRequest openAccountRequest) {
        if (balances.containsKey(openAccountRequest.account())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        balances.put(openAccountRequest.account(), openAccountRequest.initialBalance());
    }

    @PostMapping("/debit")
    public void debit(@RequestBody DebitRequest debitRequest) {
        if (!balances.containsKey(debitRequest.account())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var newBalance = balances.get(debitRequest.account()).subtract(debitRequest.amount());
        balances.put(debitRequest.account(), newBalance);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody CreditRequest creditRequest) {
        if (!balances.containsKey(creditRequest.account())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var newBalance = balances.get(creditRequest.account()).add(creditRequest.amount());
        balances.put(creditRequest.account(), newBalance);
    }

    @GetMapping("/accounts/{account}")
    public AccountBalanceResponse getBalance(@PathVariable String account) {
        if (!balances.containsKey(account)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var balance = balances.get(account);
        return new AccountBalanceResponse(account, balance);
    }
}
