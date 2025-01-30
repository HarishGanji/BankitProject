package com.bankit.application.serviceimplementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankit.application.entities.Account;
import com.bankit.application.exceptions.AccountAlreadyExistException;
import com.bankit.application.repository.AccountRepository;
import com.bankit.application.service.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {

	@Autowired
	AccountRepository accRepo;

	@Override
	public Account createAccount(Account acc) throws AccountAlreadyExistException {
		if (accRepo.findByAccountNumber(acc.getAccountNumber()) == null) {
			Account ac = new Account();
			ac.setAccountHolderName(acc.getAccountHolderName());
			ac.setAccountNumber(acc.getAccountNumber());
			ac.setBalance(acc.getBalance());
			return accRepo.save(ac);
		} else {
			throw new AccountAlreadyExistException();
		}

	}

	@Override
	public Account getAccountById(UUID accountId) {
		return accRepo.findById(accountId).orElseThrow(null);
	}

}
