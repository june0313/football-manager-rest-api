package com.jun.fm.service;

import com.jun.fm.controller.exception.GameNotFoundException;
import com.jun.fm.domain.application.Application;
import com.jun.fm.domain.application.ApplicationState;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.game.GameState;
import com.jun.fm.domain.player.Player;
import com.jun.fm.dto.ApplicationDto;
import com.jun.fm.repository.ApplicationRepository;
import com.jun.fm.repository.GameRepository;
import com.jun.fm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Created by wayne on 2017. 6. 6..
 *
 */
@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public ApplicationDto apply(Long gameId, ApplicationDto applicationDto) {
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

		Application application = new Application();
		application.setClub(club);
		application.setGame(game);
		application.setMessage(applicationDto.getMessage());
		application.setState(ApplicationState.PENDING);

		return ApplicationDto.from(applicationRepository.save(application));
	}

	public List<Application> findByGameId(Long gameId, Pageable pageable) {
		Game game = gameRepository.findOne(gameId);

		if (game == null) {
			throw new GameNotFoundException(gameId);
		}

		Page<Application> applicationsPage = applicationRepository.findAllByGame(game, pageable);
		return applicationsPage.getContent();
	}

	public void accept(Long gameId, Long applicationId) {
		Game game = gameRepository.findOne(gameId);

		List<Application> applications = game.getApplications();

		applications.forEach(application -> {
			if (applicationId.equals(application.getId())) {
				application.setState(ApplicationState.ACCEPTED);
			} else {
				application.setState(ApplicationState.REJECTED);
			}
		});

		game.setState(GameState.CLOSED);

	}
}
