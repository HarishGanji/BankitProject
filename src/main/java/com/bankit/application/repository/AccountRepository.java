package com.bankit.application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankit.application.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

	@Query("SELECT a FROM Account a WHERE a.accountNumber = :accountNumber")
	Account findByAccountNumber(@Param("accountNumber") long accountNumber);
}
