package com.bk.coder.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bk.coder.payload.ApiResponse;

@RestControllerAdvice
public class GlobalhandleException {
	
	@Autowired
	private ApiResponse apiResponse;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		apiResponse.setMessage(message);
		apiResponse.setSuccess(true);
		apiResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
}
