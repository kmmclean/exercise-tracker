package com.example.exercisetracker.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		// TODO: Add logging for these exceptions.
		return "redirect:/";
	}
}
