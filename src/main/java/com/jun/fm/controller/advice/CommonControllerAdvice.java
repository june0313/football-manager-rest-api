package com.jun.fm.controller.advice;

import com.jun.fm.controller.exception.*;
import com.jun.fm.controller.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wayne on 2017. 5. 31..
 * Controller 예외 처리
 */
@ControllerAdvice
@RestController
public class CommonControllerAdvice {

	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error playerNotFound(PlayerNotFoundException e) {
		return Error.of(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(ClubCreationFailureException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error clubCreationFail(ClubCreationFailureException e) {
		return Error.of(HttpStatus.CONFLICT.value(), "Fail to create club");
	}

	@ExceptionHandler(ClubNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error clubNotFound(ClubNotFoundException e) {
		return Error.of(HttpStatus.NOT_FOUND.value(), String.format("Club %d not found", e.getClubId()));
	}

	@ExceptionHandler(GameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error gameNotFound(GameNotFoundException e) {
		return Error.of(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}

	@ExceptionHandler(PlayerAlreadyBelongsToClubException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public Error playerAlreadyBelongsToClub(PlayerAlreadyBelongsToClubException e) {
		return Error.of(HttpStatus.CONFLICT.value(), String.format("Fail to create club. %s", e.getMessage()));
	}

}
