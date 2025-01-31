package com.bankit.application.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID accountId;
	private String accountHolderName;
	private long accountNumber;
	@NotNull
	private double balance;
}