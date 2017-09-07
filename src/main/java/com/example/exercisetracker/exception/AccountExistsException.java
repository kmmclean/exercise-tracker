package com.example.exercisetracker.exception;

public class AccountExistsException extends Exception {
	public AccountExistsException(String message) {
		super(message);
	}
}
