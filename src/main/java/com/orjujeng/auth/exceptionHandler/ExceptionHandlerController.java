package com.orjujeng.auth.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orjujeng.auth.exception.AccessDenideException;
import com.orjujeng.auth.exception.AuthAccessDeniedException;
import com.orjujeng.auth.utils.Result;
import com.orjujeng.auth.utils.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
	@ResponseBody
	@ExceptionHandler(value = AccessDenideException.class)
	public Result accountNotExistExceptionHandler(AccessDenideException e){
		log.error(e.getMessage(),e);
       	return Result.error(ResultCode.ACCESS_DENIDE.code,e.getMessage(),null);
    }
	
	@ResponseBody
	@ExceptionHandler(value = AuthAccessDeniedException.class)
	public Result accountNotExistExceptionHandler(AuthAccessDeniedException e){
		log.error(e.getMessage(),e);
       	return Result.error(ResultCode.AUTH_ACCESS_DENIDE.code,e.getMessage(),null);
    }
}
