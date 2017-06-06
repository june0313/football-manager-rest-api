package com.jun.fm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.player.Position;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by wayne on 2017. 5. 27..
 *
 */
@Data
public class PlayerDto {

	private Long id;
	private String name;
	private String email;
	@JsonInclude(Include.NON_NULL)
	private String password;
	private Position position;

	public static PlayerDto from(Player player) {
		PlayerDto playerDto = new PlayerDto();
		playerDto.setId(player.getId());
		playerDto.setEmail(player.getEmail());
		playerDto.setName(player.getName());
		playerDto.setPosition(player.getPosition());
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
