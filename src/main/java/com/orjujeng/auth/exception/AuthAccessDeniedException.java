package com.orjujeng.auth.exception;

public class AuthAccessDeniedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public AuthAccessDeniedException(String errMsg) {
        super(errMsg);
	}
}
