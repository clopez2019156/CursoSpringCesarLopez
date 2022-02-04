package com.bolsadeideas.springboot.error.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bolsadeideas.springboot.error.app.errors.UserNotFound;

@ControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(ArithmeticException.class)
	public String arithmeticError(Exception ex, Model model) {
		
		model.addAttribute("error", "Arithmetic error");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/arithmetic";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberExcepction(Exception ex, Model model) {
		
		model.addAttribute("error", "Number Format error");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		
		return "error/number-format";
	}
	
	@ExceptionHandler(UserNotFound.class)
	public String userNotFound(UserNotFound ex, Model model) {
		
		model.addAttribute("error", "User does not exist");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		
		return "error/number-format";
	}

}
