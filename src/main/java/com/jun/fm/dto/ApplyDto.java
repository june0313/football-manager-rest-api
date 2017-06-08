package com.jun.fm.dto;

import com.jun.fm.domain.apply.Apply;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Data
public class ApplyDto {

	private Long id;
	private Long gameId;
	private Long clubId;
	private String message;
	private LocalDateTime createdDate;

	public static ApplyDto from(Apply entity) {
		ApplyDto applyDto = new ApplyDto();
		applyDto.setId(entity.getId());
		applyDto.setMessage(entity.getMessage());
		applyDto.setClubId(entity.getClub().getId());
		applyDto.setGameId(entity.getGame().getId());
		applyDto.setCreatedDate(entity.getCreatedDate());

		return applyDto;
	}

}
