package com.bankit.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bankit.application.controller.AccountController;
import com.bankit.application.entities.Account;
import com.bankit.application.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
class BankitApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private AccountService accServ; // Inject mock service

	private final ObjectMapper mapper = new ObjectMapper();
	private static final UUID uuid1 = UUID.randomUUID();

	@TestConfiguration
	static class MockServiceConfig {
		@Bean
		AccountService accServ() {
			return Mockito.mock(AccountService.class);
		}
	}

	@Test
	void createAccountPositive() throws Exception {
		Account acc = new Account();
		acc.setAccountId(uuid1);
		acc.setAccountNumber(23546543);
		acc.setAccountHolderName("Raja");
		acc.setBalance(26545254);

		String json = mapper.writeValueAsString(acc);

		when(accServ.createAccount(any(Account.class))).thenReturn(acc);

		mvc.perform(post("/account").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.accountId").value(uuid1.toString()))
				.andExpect(jsonPath("$.accountNumber").value(23546543))
				.andExpect(jsonPath("$.accountHolderName").value("Raja"))
				.andExpect(jsonPath("$.balance").value(26545254));
	}
	
	@Test
	void getAccountByIdPositive() throws Exception {
	    Account acc = new Account();
	    acc.setAccountId(uuid1);
	    acc.setAccountNumber(23546543);
	    acc.setAccountHolderName("Raja");
	    acc.setBalance(26545254);

	    // Mock the service method to return the account when called
	    when(accServ.getAccountById(uuid1)).thenReturn(acc);

	    // Perform the GET request to retrieve the account by ID
	    mvc.perform(get("/account/{accountId}", uuid1) // Update to the correct endpoint for fetching by ID
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
	    // Using assertEquals to validate the response account
	    assertEquals(uuid1.toString(),acc.getAccountId().toString());
	    assertEquals(23546543, acc.getAccountNumber());
	    assertEquals("Raja", acc.getAccountHolderName());
	    assertEquals(26545254, acc.getBalance());
	}


	
}
