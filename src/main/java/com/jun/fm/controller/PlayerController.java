package com.jun.fm.controller;

import com.jun.fm.controller.dto.PlayerDto;
import com.jun.fm.controller.exception.PlayerNotFoundException;
import com.jun.fm.domain.player.Player;
import com.jun.fm.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@RestController
@RequestMapping("/players")
@Slf4j
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/{id}")
	public PlayerDto getById(@PathVariable Long id) {
		return Optional.ofNullable(playerService.findById(id))
			.map(PlayerDto::from)
			.orElseThrow(() -> new PlayerNotFoundException(id));
	}

	@GetMapping
	public Page<PlayerDto> getPlayers(Pageable pageable) {
		return playerService.findList(pageable);
	}

	@PostMapping
	public PlayerDto create(@RequestBody PlayerDto playerDto) {
		Player player = playerService.create(playerDto);
		return Optional.ofNullable(player).map(PlayerDto::from).orElseThrow(RuntimeException::new);
	}

}
