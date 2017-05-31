package com.jun.fm.service;

import com.jun.fm.controller.dto.PlayerDto;
import com.jun.fm.domain.player.Player;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Service
public class PlayerService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PlayerRepository playerRepository;

	public Player findById(Long id) {
		return playerRepository.findOne(id);
	}

	public Player findByName(String name) {
		return playerRepository.findPlayerByName(name);
	}

	public Player create(PlayerDto dto) {
		Player player = Player.create()
			.setName(dto.getName())
			.setEmail(dto.getEmail())
			.setPosition(dto.getPosition())
			.setPassword(passwordEncoder.encode(dto.getPassword()));

		return playerRepository.save(player);
	}

}
