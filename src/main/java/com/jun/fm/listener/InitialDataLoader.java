package com.jun.fm.listener;

import com.google.common.collect.ImmutableList;
import com.jun.fm.annotation.profile.LocalProfile;
import com.jun.fm.domain.club.Club;
import com.jun.fm.domain.game.Game;
import com.jun.fm.domain.game.GameState;
import com.jun.fm.domain.player.Player;
import com.jun.fm.domain.player.Position;
import com.jun.fm.repository.ClubRepository;
import com.jun.fm.repository.GameRepository;
import com.jun.fm.repository.PlayerRepository;
import com.jun.fm.service.PlayerService;
import com.jun.fm.service.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by wayne on 2017. 5. 26..
 *
 */
@Component
@LocalProfile
@Transactional
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ClubRepository clubRepository;

	@Autowired
	private GameRepository gameRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createPlayersForLocalTest();
		createClubs();
		createGames();
	}

	private void createPlayersForLocalTest() {
		ImmutableList.of(
			PlayerDto.of("player1", "player1@test.com", "password", Position.DEFENDER),
			PlayerDto.of("player2", "player2@test.com", "password", Position.FORWARD),
			PlayerDto.of("player3", "player3@test.com", "password", Position.MIDFIELDER),
			PlayerDto.of("player4", "player4@test.com", "password", Position.GOALKEEPER)
		).forEach(playerService::create);
	}

	private void createClubs() {
		Club club = Club.create().setName("club1");

		Player player1 = playerRepository.findOne(1L);
		Player player2 = playerRepository.findOne(2L);

		player1.setClub(club);
		player2.setClub(club);

		clubRepository.save(club);
		playerRepository.save(ImmutableList.of(player1, player2));
	}

	private void createGames() {
		Club club = clubRepository.findOne(1L);

		Game game = new Game();
		game.setHost(club);
		game.setState(GameState.OPEN);
		game.setDetails("게임해요");
		game.setMatchLocation("관악구 축구장");
		game.setMatchDate(LocalDateTime.now().plusDays(7));

		gameRepository.save(game);
	}

}
