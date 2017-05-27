package com.jun.fm.controller.dto;

import com.jun.fm.domain.club.Club;
import lombok.Getter;

/**
 * Created by wayne on 2017. 5. 27..
 * 클럽 생성시 필요한 데이터를 전달하기 위한 DTO
 */
@Getter
public class ClubCreationDto {

	private Long ownerId;
	private String clubName;

	public Club getClub() {
		return Club.create().setName(clubName);
	}
}
