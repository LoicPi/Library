package com.books.exceptions;

public class AuthorNotFoundException extends RuntimeException {
	
	public AuthorNotFoundException (String message) {
		super(message);
	}

}
