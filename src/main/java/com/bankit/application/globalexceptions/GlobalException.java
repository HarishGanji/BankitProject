package com.bankit.application.globalexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bankit.application.exceptions.AccountAlreadyExistException;
//@ControllerAdvice 
public class GlobalException {

	@ExceptionHandler(AccountAlreadyExistException.class)
	public ResponseEntity<String> handleAccountAlreadyExistException(AccountAlreadyExistException ex) {
		return new ResponseEntity<>("Account already exists in the database. Please try a different account.",
				HttpStatus.BAD_REQUEST);
	}
}
