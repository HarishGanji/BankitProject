package com.bankit.application.service;

import java.util.List;
import java.util.UUID;

import com.bankit.application.dto.AccountDTO;
import com.bankit.application.entities.Account;
import com.bankit.application.exceptions.AccountAlreadyExistException;

public interface AccountService {

	Account createAccount(Account acc) throws AccountAlreadyExistException;

	Account getAccountById(UUID accountId);

	Account deposit(UUID accountId, double amount);

	Account withdraw(UUID accountId, double amount);

	List<Account> getAll();

}
