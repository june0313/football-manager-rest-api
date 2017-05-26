package com.jun.fm.controller;

import com.jun.fm.controller.exception.Error;
import com.jun.fm.controller.exception.PlayerNotFoundException;
import com.jun.fm.domain.player.Player;
import com.jun.fm.service.PlayerService;
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
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping("/{id}")
	public Player getById(@PathVariable Long id) {
		return Optional.ofNullable(playerService.findById(id)).orElseThrow(() -> new PlayerNotFoundException(id));
	}

	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error playerNotFound(PlayerNotFoundException e) {
		Long playerId = e.getPlayerId();
		return Error.of(HttpStatus.NOT_FOUND.value(), String.format("Player %d not found", playerId));
	}

}
