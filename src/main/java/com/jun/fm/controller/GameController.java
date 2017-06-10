package com.jun.fm.controller;

import com.jun.fm.dto.GameDto;
import com.jun.fm.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by wayne on 2017. 6. 3..
 *
 */
@RestController
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping("/{id}")
	public GameDto getById(@PathVariable Long id) {
		GameDto gameDto = gameService.findById(id);
		return Optional.ofNullable(gameDto).orElseThrow(RuntimeException::new);
	}

	@PostMapping
	public GameDto create(@RequestBody GameDto gameDto) {
		return gameService.create(gameDto);
	}

}
