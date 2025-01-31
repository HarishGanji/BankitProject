package com.bankit.application.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankit.application.dto.AmountUpdateRequest;
import com.bankit.application.entities.Account;
import com.bankit.application.enumeration.TransactionType;
import com.bankit.application.exceptions.AccountAlreadyExistException;
import com.bankit.application.service.AccountService;

import jakarta.persistence.Enumerated;

@RestController
public class AccountController {

	@Autowired
	AccountService accServ;

	@PostMapping("/account")
	public ResponseEntity<Account> post(@RequestBody Account account) throws AccountAlreadyExistException {
		return new ResponseEntity<>(accServ.createAccount(account), HttpStatus.OK);
	}

	@GetMapping("/account/{accountId}")
	public ResponseEntity<Account> get(@PathVariable UUID accountId) {
		return new ResponseEntity<>(accServ.getAccountById(accountId), HttpStatus.OK);
	}

	@PutMapping("/{accountId}/balance")
	public ResponseEntity<Account> updateBalance(@PathVariable UUID accountId, @RequestParam TransactionType trans,
			@RequestParam double amount) {
		Account updatedAccount;
		if (trans.equals(TransactionType.DEBIT)) {
			updatedAccount = accServ.withdraw(accountId, amount); // Withdraw operation
		} else if (trans.equals(TransactionType.WITHDRAW)) {
			updatedAccount = accServ.deposit(accountId, amount); // Deposit operation
		} else {
			throw new IllegalArgumentException("Invalid transaction type: " + trans);
		}
		return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAll() {
		return new ResponseEntity<>(accServ.getAll(), HttpStatus.OK);
	}
}
