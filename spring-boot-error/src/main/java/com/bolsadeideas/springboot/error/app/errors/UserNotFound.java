package com.bolsadeideas.springboot.error.app.errors;

public class UserNotFound extends RuntimeException {

	

	public UserNotFound(String id) {
		super("User with ID: ".concat(id).concat(" does'nt exist in the system"));
	}

	private static final long serialVersionUID = 1L;

}
