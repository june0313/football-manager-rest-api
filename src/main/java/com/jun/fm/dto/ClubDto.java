package com.jun.fm.dto;

import com.jun.fm.domain.club.Club;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by wayne on 2017. 5. 27..
 *
 */
@Setter
@Getter
public class ClubDto {

	private Long id;
	private String name;

	public static ClubDto from(Club club) {
		ClubDto clubDto = new ClubDto();
		clubDto.setId(club.getId());
		clubDto.setName(club.getName());
		return clubDto;
	}

}
