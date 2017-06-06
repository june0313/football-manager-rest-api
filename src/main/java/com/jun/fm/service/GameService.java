package com.jun.fm.service;

import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.player.Player;
import com.jun.fm.repository.GameRepository;
import com.jun.fm.repository.PlayerRepository;
import com.jun.fm.service.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by wayne on 2017. 6. 3..
 *
 */
@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private PlayerRepository playerRepository;

	public GameDto findById(Long id) {
		Game game = gameRepository.findOne(id);

		if (game == null) {
			return null;
		}

		return GameDto.from(game);
	}

	public GameDto create(GameDto gameDto) {
		SecurityContext context = SecurityContextHolder.getContext();

		Club club = Optional.ofNullable(context)
			.map(SecurityContext::getAuthentication)
			.map(Principal::getName)
			.map(playerRepository::findPlayerByName)
			.map(Player::getClub)
			.orElseThrow(() -> new RuntimeException("no club"));

		Game game = new Game();
		game.setHost(club);
		game.setMatchDate(gameDto.getMatchDate());
		game.setDetails(gameDto.getDetails());
		game.setMatchLocation(gameDto.getMatchLocation());

		return GameDto.from(gameRepository.save(game));
	}
}
