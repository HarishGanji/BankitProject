package com.bankit.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankit.application.dto.AccountDTO;
import com.bankit.application.entities.Account;
import com.bankit.application.exceptions.AccountAlreadyExistException;
import com.bankit.application.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accServ;

	@PostMapping("/account")
	public ResponseEntity<Account> post(@RequestBody Account account) throws AccountAlreadyExistException {
		return new ResponseEntity<>(accServ.createAccount(account), HttpStatus.OK);
	}

	@GetMapping("/retrieveaccount/{accountId}")
	public ResponseEntity<Account> get(@PathVariable UUID accountId) {
		return new ResponseEntity<>(accServ.getAccountById(accountId), HttpStatus.OK);
	}
}
