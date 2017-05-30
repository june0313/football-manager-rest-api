package com.jun.fm.controller.dto;

import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.player.Position;
import lombok.Data;

/**
 * Created by wayne on 2017. 5. 27..
 *
 */
@Data
public class PlayerDto {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String position;

	public Player toEntity() {
		return Player.create()
			.setName(getName())
			.setEmail(getEmail())
			.setPassword(getPassword())
			.setPosition(Position.valueOf(getPosition()));
	}

	public static PlayerDto from(Player player) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setId(player.getId());
		playerDto.setEmail(player.getEmail());
		playerDto.setName(player.getName());
		return playerDto;
	}

}
