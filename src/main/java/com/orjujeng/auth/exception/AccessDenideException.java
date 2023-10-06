package com.orjujeng.auth.exception;

public class AccessDenideException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errMsg;
	public AccessDenideException(String errMsg) {
        super(errMsg);
	}
}
