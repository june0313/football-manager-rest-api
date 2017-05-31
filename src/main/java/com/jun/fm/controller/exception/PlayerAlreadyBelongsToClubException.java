package com.jun.fm.controller.exception;

/**
 * Created by wayne on 2017. 5. 31..
 *
 */
public class PlayerAlreadyBelongsToClubException extends RuntimeException {

	public PlayerAlreadyBelongsToClubException(Long playerId, Long clubId) {
		super(String.format("Player %d already belongs to club %d", playerId, clubId));
	}

}
