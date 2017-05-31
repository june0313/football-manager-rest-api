package com.jun.fm.controller.exception;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException(Long id) {
		super(String.format("Player %d not found", id));
	}

	public PlayerNotFoundException(String name) {
		super(String.format("Player %s not found", name));
	}

}
