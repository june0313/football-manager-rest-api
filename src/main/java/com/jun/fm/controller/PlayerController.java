package com.jun.fm.controller;

import com.jun.fm.controller.dto.PlayerDto;
import com.jun.fm.controller.exception.Error;
import com.jun.fm.controller.exception.PlayerNotFoundException;
import com.jun.fm.domain.player.Player;
import com.jun.fm.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public Player getById(@PathVariable Long id) {
		return Optional.ofNullable(playerService.findById(id)).orElseThrow(() -> new PlayerNotFoundException(id));
	}

	@PostMapping
	public PlayerDto create(@RequestBody PlayerDto playerDto) {
		log.info("playerDTO : {}", playerDto);
		Player player = playerService.create(playerDto.toEntity());
		return Optional.ofNullable(player).map(PlayerDto::from).orElseThrow(RuntimeException::new);
	}

	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error playerNotFound(PlayerNotFoundException e) {
		Long playerId = e.getPlayerId();
		return Error.of(HttpStatus.NOT_FOUND.value(), String.format("Player %d not found", playerId));
	}

}
