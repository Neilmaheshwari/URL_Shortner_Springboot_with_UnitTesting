package com.UrlProject.ShortnerUrl.errorhandler;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.UrlProject.ShortnerUrl.exception.NoSuchUrlExistException;
import com.UrlProject.ShortnerUrl.exception.NoReportExistsException;
import com.UrlProject.ShortnerUrl.exception.NoUserExistException;
import com.UrlProject.ShortnerUrl.exception.NotExceptableUrlException;
import com.UrlProject.ShortnerUrl.exception.UrlNotValidAnymoreException;

import com.UrlProject.ShortnerUrl.exception.ReportDateException;
import com.UrlProject.ShortnerUrl.exception.NoShortUrlWithUserIdException;

import com.UrlProject.ShortnerUrl.exception.UrlLimitOnUserException;

import com.UrlProject.ShortnerUrl.exception.NoUrlVisitedTillException;

@ControllerAdvice
public class ErrorHandler {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoSuchElementException.class)
	protected final ResponseEntity handleNoSuchElementException(NoSuchElementException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoSuchMethodException.class)
	protected final ResponseEntity handleCustomException(NoSuchMethodException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NotExceptableUrlException.class)
	protected final ResponseEntity handleUrlNotExceptableException(NotExceptableUrlException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UrlNotValidAnymoreException.class)
	protected final ResponseEntity handleUrlNotValidException(UrlNotValidAnymoreException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ReportDateException.class)
	protected final ResponseEntity ReportDateException(ReportDateException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoReportExistsException.class)
	protected final ResponseEntity NoReportException(NoReportExistsException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoSuchUrlExistException.class)
	protected final ResponseEntity ReportNoInvalidUrlException(NoSuchUrlExistException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoUserExistException.class)
	protected final ResponseEntity InvalidUserException(NoUserExistException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoShortUrlWithUserIdException.class)
	protected final ResponseEntity NoShortUrlWithUserIdException(NoShortUrlWithUserIdException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UrlLimitOnUserException.class)
	protected final ResponseEntity UrlLimitOnUserException(UrlLimitOnUserException ex) {
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
	}
	
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoUrlVisitedTillException.class)
	protected final ResponseEntity NoUrlVisitedTillException(NoUrlVisitedTillException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
