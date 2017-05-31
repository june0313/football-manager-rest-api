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
	private Position position;

	public static PlayerDto from(Player player) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setId(player.getId());
		playerDto.setEmail(player.getEmail());
		playerDto.setName(player.getName());
		return playerDto;
	}

	public static PlayerDto of(String name, String email, String password, Position position) {
		PlayerDto dto = new PlayerDto();
		dto.setName(name);
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setPosition(position);
		return dto;
	}
}
