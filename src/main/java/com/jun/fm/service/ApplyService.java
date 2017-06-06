package com.jun.fm.service;

import com.jun.fm.controller.exception.GameNotFoundException;
import com.jun.fm.domain.apply.Apply;
import com.jun.fm.domain.apply.ApplyState;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.player.Player;
import com.jun.fm.dto.ApplyDto;
import com.jun.fm.repository.ApplyRepository;
import com.jun.fm.repository.GameRepository;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Service
@Transactional
public class ApplyService {

	@Autowired
	private ApplyRepository applyRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public ApplyDto apply(Long gameId, ApplyDto applyDto) {
		SecurityContext context = SecurityContextHolder.getContext();

		Club club = Optional.ofNullable(context)
			.map(SecurityContext::getAuthentication)
			.map(Principal::getName)
			.map(playerRepository::findPlayerByName)
			.map(Player::getClub)
			.orElseThrow(() -> new RuntimeException("no club"));

		Game game = gameRepository.findOne(gameId);
		if (game == null) {
			throw new GameNotFoundException(gameId);
		}

		Apply apply = new Apply();
		apply.setClub(club);
		apply.setGame(game);
		apply.setMessage(applyDto.getMessage());
		apply.setState(ApplyState.PENDING);

		return ApplyDto.from(applyRepository.save(apply));
	}

}
