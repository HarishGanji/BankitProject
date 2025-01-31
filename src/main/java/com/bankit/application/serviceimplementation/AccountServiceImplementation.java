package com.bankit.application.serviceimplementation;

import java.util.List;
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
		return accRepo.getAccountId(accountId);
	}

	@Override
	public Account deposit(UUID accountId, double amount) {
		Account account = accRepo.getAccountId(accountId);
		if (account != null) {
			account.setBalance(account.getBalance() + amount);
			accRepo.save(account);
		}
		return account;
	}

	@Override
	public Account withdraw(UUID accountId, double amount) {
		Account account = accRepo.getAccountId(accountId);
		if (account != null && account.getBalance() > amount) {
			account.setBalance(account.getBalance() - amount);
			accRepo.save(account);
		}
		return account;
	}

	@Override
	public List<Account> getAll() {
		return accRepo.getAll();
	}

}
