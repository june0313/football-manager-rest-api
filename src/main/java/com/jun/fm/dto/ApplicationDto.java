package com.jun.fm.dto;

import com.jun.fm.domain.application.Application;
import com.jun.fm.domain.application.ApplicationState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Data
public class ApplicationDto {

	private Long id;
	private Long gameId;
	private Long clubId;
	private String message;
	private ApplicationState state;
	private LocalDateTime createdDate;

	public static ApplicationDto from(Application entity) {
		ApplicationDto applicationDto = new ApplicationDto();
		applicationDto.setId(entity.getId());
		applicationDto.setMessage(entity.getMessage());
		applicationDto.setClubId(entity.getClub().getId());
		applicationDto.setGameId(entity.getGame().getId());
		applicationDto.setState(entity.getState());
		applicationDto.setCreatedDate(entity.getCreatedDate());

		return applicationDto;
	}

}
