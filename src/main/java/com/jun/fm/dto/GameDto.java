package com.jun.fm.dto;

import com.jun.fm.domain.game.Game;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 6. 3..
 *
 */
@Data
public class GameDto {
	private Long id;
	private String details;
	private LocalDateTime matchDate;
	private String matchLocation;

	public static GameDto from(Game game) {
		GameDto gameDto = new GameDto();
		gameDto.setId(game.getId());
		gameDto.setDetails(game.getDetails());
		gameDto.setMatchDate(game.getMatchDate());
		gameDto.setMatchLocation(game.getMatchLocation());
		return gameDto;
	}

}
