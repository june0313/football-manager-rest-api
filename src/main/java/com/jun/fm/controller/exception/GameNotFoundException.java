package com.jun.fm.controller.exception;

/**
 * Created by wayne on 2017. 6. 6..
 */
public class GameNotFoundException extends RuntimeException {
	public GameNotFoundException(Long gameId) {
		super(String.format("Game %d not found", gameId	));
	}
}
