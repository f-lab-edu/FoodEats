package com.flab.foodeats.common.error.exception;

public class UnauthorizedException extends RuntimeException {

	private static final String message = "Unauthorized User";

	public UnauthorizedException(){
		super(message);
	}
}
